package com.backendboys.battlerace.model.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;

import java.util.Arrays;
import java.util.List;

abstract class AbstractCar extends AbstractVehicle {

    private final Chassi chassi;
    private final Motor motor;
    private final Wheels wheels;

    private RevoluteJoint rearWheelRevJoint;
    private RevoluteJoint frontWheelRevJoint;
    private RevoluteJoint rearAxlePrisJoint;
    private RevoluteJoint frontAxlePrisJoint;

    private Body rearWheel;
    private Body frontWheel;

    AbstractCar(World world, float posX, float posY, Chassi chassi, Motor motor, Wheels wheels) {
        super(chassi.getMass(), posX, posY, motor.getTopSpeed(), motor.getAcceleration(), motor.getAngularAcceleration());

        this.chassi = chassi;
        this.motor = motor;
        this.wheels = wheels;

        build(world);
    }

    private void createJoints(Body main, World world, Body rearWheel, Body frontWheel) {

        // Revolute Joints for Wheels and Axles -----------------

        // Rear
        RevoluteJointDef rearWheelRevoluteJointDef = new RevoluteJointDef();
        rearWheelRevoluteJointDef.bodyA = main;
        rearWheelRevoluteJointDef.bodyB = rearWheel;
        rearWheelRevoluteJointDef.enableMotor = true;
        rearWheelRevoluteJointDef.maxMotorTorque = 30000;
        rearWheelRevoluteJointDef.localAnchorA.set(-9,0);
        world.createJoint(rearWheelRevoluteJointDef);

        // Front
        RevoluteJointDef frontWheelRevoluteJointDef = new RevoluteJointDef();
        frontWheelRevoluteJointDef.bodyA = main;
        frontWheelRevoluteJointDef.bodyB = frontWheel;
        frontWheelRevoluteJointDef.enableMotor = true;
        frontWheelRevoluteJointDef.maxMotorTorque = 30000;
        frontWheelRevoluteJointDef.localAnchorA.set(9,0);
        world.createJoint(frontWheelRevoluteJointDef);


        // Prismatic Joint for Axles and Main Body ------------------
        //RevoluteJointDef rDef = new RevoluteJointDef();



    }

    @Override
    protected Shape createVehicleShape() {

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(chassi.getWidth(), chassi.getHeight());

        return shape;
    }

    @Override
    protected float vehicleArea() {
        return chassi.getWidth()*chassi.getHeight();
    }



    @Override
    protected List<Body> initParts(Body main, World world) {

        float width = chassi.getWidth();
        float height = chassi.getHeight();



        // Creating Wheels Shape -------------------
        CircleShape wheelShape = new CircleShape();
        wheelShape.setRadius(wheels.getRadius());

        // Wheels FixtureDef
        FixtureDef wheelFixture = new FixtureDef();
        wheelFixture.density = wheels.getDensity();
        wheelFixture.friction = wheels.getFriction();
        wheelFixture.restitution = wheels.getRestitution();
        wheelFixture.filter.groupIndex = -1;
        wheelFixture.shape = wheelShape;

        // Wheels BodyDef
        BodyDef wheelBodyDef = new BodyDef();
        wheelBodyDef.type = BodyDef.BodyType.DynamicBody;
        wheelBodyDef.position.set(getWorldCenter().x - (width), getWorldCenter().y - (height));

        // Rear Wheel Body
        Body rearWheel = world.createBody(wheelBodyDef);
        rearWheel.createFixture(wheelFixture);

        wheelBodyDef.position.set(getWorldCenter().x + (width), getWorldCenter().y - (height));

        // Front Wheel Body
        Body frontWheel = world.createBody(wheelBodyDef);
        frontWheel.createFixture(wheelFixture);

        createJoints(main, world, rearWheel, frontWheel);

        this.frontWheel = frontWheel;
        this.rearWheel = rearWheel;

        return Arrays.asList(rearWheel, frontWheel);
    }



    @Override
    public void gas() {
        frontWheel.applyForce(2*-12500f, 0f, 0f, 0, true);
        rearWheel.applyForce(2*-12500f, 0f, 0f, 0, true);
    }

    @Override
    public void brake() {
        frontWheel.applyForce(2*12500f, 0f, 0f, 0, true);
        rearWheel.applyForce(2*12500f, 0f, 0f, 0, true);
    }

}
