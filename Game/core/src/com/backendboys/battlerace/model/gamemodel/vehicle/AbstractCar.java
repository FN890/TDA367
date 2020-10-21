package com.backendboys.battlerace.model.gamemodel.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import java.util.Arrays;
import java.util.List;

/**
 * AbstractCar extends AbstractVehicle
 */
abstract class AbstractCar extends AbstractVehicle implements ICar {

    private final Chassi chassi;
    private final Motor motor;
    private final Wheels wheels;

    private Body rearWheel;
    private Body frontWheel;

    /**
     * @param world  GameWorld holding all game objects.
     * @param posX   Spawn position in world.
     * @param posY   Spawn position in world.
     * @param chassi Body part of car, holds width, height and mass.
     * @param motor  Car motor keeping track of TopSpeed, Acceleration and AngularAcceleration.
     * @param wheels Holding wheel radius, friction, density and restitution.
     */
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
        final RevoluteJointDef rearWheelRevoluteJointDef = new RevoluteJointDef();
        rearWheelRevoluteJointDef.bodyA = main;
        rearWheelRevoluteJointDef.bodyB = rearWheel;
        rearWheelRevoluteJointDef.enableMotor = true;
        rearWheelRevoluteJointDef.maxMotorTorque = 3000;
        rearWheelRevoluteJointDef.motorSpeed = 10000;
        rearWheelRevoluteJointDef.localAnchorA.set(-16, -5);
        world.createJoint(rearWheelRevoluteJointDef);

        // Front
        final RevoluteJointDef frontWheelRevoluteJointDef = new RevoluteJointDef();
        frontWheelRevoluteJointDef.bodyA = main;
        frontWheelRevoluteJointDef.bodyB = frontWheel;
        frontWheelRevoluteJointDef.enableMotor = false;
        frontWheelRevoluteJointDef.maxMotorTorque = 3000;
        rearWheelRevoluteJointDef.motorSpeed = 10000;
        frontWheelRevoluteJointDef.localAnchorA.set(17.5f, -5.5f);
        world.createJoint(frontWheelRevoluteJointDef);

        main.setLinearDamping(0.5f);
        main.setAngularDamping(0.5f);

        // Prismatic Joint for Axles and Main Body ------------------
        //RevoluteJointDef rDef = new RevoluteJointDef();


    }

    @Override
    protected Shape createVehicleShape() {

        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(chassi.getWidth(), chassi.getHeight());

        return shape;
    }

    @Override
    protected float vehicleArea() {
        return chassi.getWidth() * chassi.getHeight();
    }


    @Override
    protected List<Body> initParts(Body main, World world) {

        float width = chassi.getWidth();
        float height = chassi.getHeight();


        // Creating Wheels Shape -------------------
        final CircleShape wheelShape = new CircleShape();
        wheelShape.setRadius(wheels.getRadius());

        // Wheels FixtureDef
        final FixtureDef wheelFixture = new FixtureDef();
        wheelFixture.density = wheels.getDensity();
        wheelFixture.friction = wheels.getFriction();
        wheelFixture.restitution = wheels.getRestitution();
        wheelFixture.filter.groupIndex = -1;
        wheelFixture.shape = wheelShape;

        // Wheels BodyDef
        final BodyDef wheelBodyDef = new BodyDef();
        wheelBodyDef.type = BodyDef.BodyType.DynamicBody;
        wheelBodyDef.position.set(getWorldCenter().x - (width), getWorldCenter().y - (height));

        // Rear Wheel Body
        final Body rearWheel = world.createBody(wheelBodyDef);
        rearWheel.createFixture(wheelFixture);

        wheelBodyDef.position.set(getWorldCenter().x + (width), getWorldCenter().y - (height));

        // Front Wheel Body
        final Body frontWheel = world.createBody(wheelBodyDef);
        frontWheel.createFixture(wheelFixture);

        createJoints(main, world, rearWheel, frontWheel);

        this.frontWheel = frontWheel;
        this.rearWheel = rearWheel;

        return Arrays.asList(rearWheel, frontWheel);
    }

    /**
     * Apply clockwise torque to the wheels making it rotate and pushing the car forward.
     */
    @Override
    public void gas() {
        rearWheel.applyTorque(-5000 * motor.getAcceleration(), true);
        frontWheel.applyTorque(-5000 * motor.getAcceleration(), true);
    }

    /**
     * Apply counter-clockwise torque to the wheels making it rotate and pushing the car forward.
     */
    @Override
    public void brake() {
        frontWheel.applyTorque(5000 * motor.getAcceleration(), true);
        rearWheel.applyTorque(5000 * motor.getAcceleration(), true);
    }

    @Override
    public Vector2 getFrontWheelPosition() {
        return frontWheel.getPosition();
    }

    @Override
    public Vector2 getRearWheelPosition() {
        return rearWheel.getPosition();
    }

    @Override
    public float getFrontWheelRotation() {
        return frontWheel.getTransform().getRotation();
    }

    @Override
    public float getRearWheelRotation() {
        return frontWheel.getTransform().getRotation();
    }

    @Override
    public float getAcceleration() {
        return motor.getAcceleration();
    }

    @Override
    public void setAcceleration(float acceleration) {
        motor.setAcceleration(acceleration);
    }
}
