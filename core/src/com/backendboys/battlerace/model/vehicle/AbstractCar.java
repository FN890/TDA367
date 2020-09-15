package com.backendboys.battlerace.model.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import java.util.Arrays;
import java.util.List;

abstract class AbstractCar extends AbstractVehicle {

    private final Chassi chassi;
    private final Motor motor;
    private final Wheels wheels;

    private RevoluteJoint rearWheelRevJoint;
    private RevoluteJoint frontWheelRevJoint;
    private PrismaticJoint rearAxlePrisJoint;
    private PrismaticJoint frontAxlePrisJoint;

    private Body rearWheel;
    private Body frontWheel;

    AbstractCar(World world, float posX, float posY, Chassi chassi, Motor motor, Wheels wheels) {
        super(chassi.getMass(), posX, posY, motor.getTopSpeed(), motor.getAcceleration(), motor.getAngularAcceleration());

        this.chassi = chassi;
        this.motor = motor;
        this.wheels = wheels;

        build(world);
    }

    private void createJoints(Body main, World world, Body rearAxle, Body frontAxle, Body rearWheel, Body frontWheel) {

        // Revolute Joints for Wheels and Axles -----------------

        // Rear
        RevoluteJointDef rearWheelRevoluteJointDef = new RevoluteJointDef();
        rearWheelRevoluteJointDef.initialize(rearWheel, rearAxle, rearWheel.getWorldCenter());
        rearWheelRevoluteJointDef.enableMotor = true;
        rearWheelRevoluteJointDef.maxMotorTorque = 30000;
        rearWheelRevJoint = (RevoluteJoint) world.createJoint(rearWheelRevoluteJointDef);

        // Front
        RevoluteJointDef frontWheelRevoluteJointDef = new RevoluteJointDef();
        frontWheelRevoluteJointDef.initialize(frontWheel, frontAxle, frontWheel.getWorldCenter());
        frontWheelRevoluteJointDef.enableMotor = true;
        frontWheelRevoluteJointDef.maxMotorTorque = 30000;
        frontWheelRevJoint = (RevoluteJoint) world.createJoint(frontWheelRevoluteJointDef);


        // Prismatic Joint for Axles and Main Body ------------------
        PrismaticJointDef axlePrismaticJointDef = new PrismaticJointDef();
        axlePrismaticJointDef.lowerTranslation = 0;
        axlePrismaticJointDef.upperTranslation = 0;
        axlePrismaticJointDef.enableLimit = true;
        axlePrismaticJointDef.enableMotor = true;

        // Front
        axlePrismaticJointDef.initialize(main, frontAxle, frontAxle.getWorldCenter(), new Vector2(0,1));
        frontAxlePrisJoint = (PrismaticJoint) world.createJoint(axlePrismaticJointDef);

        // Rear
        axlePrismaticJointDef.initialize(main, rearAxle, rearAxle.getWorldCenter(), new Vector2(0,1));
        rearAxlePrisJoint = (PrismaticJoint) world.createJoint(axlePrismaticJointDef);

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

        // Creating Axles Shape ------------------------
        PolygonShape axle = new PolygonShape();
        axle.setAsBox(width * 0.1f, height * 0.3f);

        // Axles FixtureDef
        FixtureDef axleFixture = new FixtureDef();
        axleFixture.density = 0.5f;
        axleFixture.friction = 3;
        axleFixture.restitution = 0.3f;
        axleFixture.filter.groupIndex = -1;
        axleFixture.shape = axle;

        // Axles BodyDef
        BodyDef axleBodyDef = new BodyDef();
        axleBodyDef.type = BodyDef.BodyType.DynamicBody;
        axleBodyDef.position.set(getWorldCenter().x - (width), getWorldCenter().y - (height));

        // Rear Axle Body
        Body rearAxle = world.createBody(axleBodyDef);
        rearAxle.createFixture(axleFixture);

        axleBodyDef.position.set(getWorldCenter().x + (width), getWorldCenter().y - (height));

        // Front Axle Body
        Body frontAxle = world.createBody(axleBodyDef);
        frontAxle.createFixture(axleFixture);

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

        createJoints(main, world, rearAxle, frontAxle, rearWheel, frontWheel);

        this.frontWheel = frontWheel;
        this.rearWheel = rearWheel;

        return Arrays.asList(rearAxle, frontAxle, rearWheel, frontWheel);
    }



    @Override
    public void gas() {
        frontWheel.applyForce(-5000f, 0f, 0f, 0, true);
        rearWheel.applyForce(-5000f, 0f, 0f, 0, true);
    }

    @Override
    public void brake() {
        super.brake();
    }

}
