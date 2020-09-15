package com.backendboys.battlerace.model.vehicle;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

abstract class Car extends AbstractVehicle {

    float width;
    float height;

    Car(float width, float height, World world, float mass, float posX, float posY, float topSpeed, float acceleration, float angularAcceleration) {
        super(world, mass, posX, posY, topSpeed, acceleration, angularAcceleration);

        this.width = width;
        this.height = height;
    }

    @Override
    protected Shape createVehicleShape() {

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        return shape;
    }

    @Override
    protected float vehicleArea() {
        return width*height;
    }

    // TODO: Cleanup
    @Override
    protected void initJoints(Body main, World world) {

        // Creating Axles
        PolygonShape axle = new PolygonShape();
        axle.setAsBox(width * 0.2f, height * 0.3f);

        BodyDef axleBodyDef = new BodyDef();
        axleBodyDef.type = BodyDef.BodyType.DynamicBody;
        axleBodyDef.position.set(getWorldCenter().x - (width/2) + 10, getWorldCenter().y - (height/2));

        Body rearAxle = world.createBody(axleBodyDef);

        FixtureDef axelFixture = new FixtureDef();
        axelFixture.density = 0.5f;
        axelFixture.friction = 3;
        axelFixture.restitution = 0.3f;
        axelFixture.filter.groupIndex = -1;
        axelFixture.shape = axle;
        rearAxle.createFixture(axelFixture);

        axleBodyDef.position.set(getWorldCenter().x + (width/2) - 10, getWorldCenter().y - (height/2));
        Body frontAxle = world.createBody(axleBodyDef);
        frontAxle.createFixture(axelFixture);

        // Creating Wheels
        CircleShape wheelShape = new CircleShape();
        wheelShape.setRadius(5);

        BodyDef wheelBodyDef = new BodyDef();
        wheelBodyDef.type = BodyDef.BodyType.DynamicBody;
        wheelBodyDef.position.set(getWorldCenter().x - (width/2) + 10, getWorldCenter().y - (height/2));

        Body rearWheel = world.createBody(wheelBodyDef);

        FixtureDef wheelFixture = new FixtureDef();
        wheelFixture.density = 1;
        wheelFixture.friction = 3;
        wheelFixture.restitution = 0.1f;
        wheelFixture.filter.groupIndex = -1;
        wheelFixture.shape = wheelShape;

        rearWheel.createFixture(wheelFixture);

        wheelBodyDef.position.set(getWorldCenter().x + (width/2) - 10, getWorldCenter().y - (height/2));

        Body frontWheel = world.createBody(wheelBodyDef);
        frontWheel.createFixture(wheelFixture);

        // Revolute Joints
        RevoluteJointDef rearWheelRevoluteJointDef = new RevoluteJointDef();
        rearWheelRevoluteJointDef.initialize(rearWheel, rearAxle, rearWheel.getWorldCenter());
        rearWheelRevoluteJointDef.enableMotor = true;
        rearWheelRevoluteJointDef.maxMotorTorque = 10000;
        Joint rearWheelRevoluteJoint = world.createJoint(rearWheelRevoluteJointDef);

        RevoluteJointDef frontWheelRevoluteJointDef = new RevoluteJointDef();
        frontWheelRevoluteJointDef.initialize(frontWheel, frontAxle, frontWheel.getWorldCenter());
        frontWheelRevoluteJointDef.enableMotor = true;
        frontWheelRevoluteJointDef.maxMotorTorque = 10000;
        Joint frontWheelRevoluteJoint = world.createJoint(rearWheelRevoluteJointDef);


        // Prismatic Joint
        PrismaticJointDef axlePrismaticJointDef = new PrismaticJointDef();
        axlePrismaticJointDef.lowerTranslation = -10;
        axlePrismaticJointDef.upperTranslation = 3;
        axlePrismaticJointDef.enableLimit = true;
        axlePrismaticJointDef.enableMotor = true;

        axlePrismaticJointDef.initialize(main, frontAxle, frontAxle.getWorldCenter(), new Vector2(0,1));
        Joint frontAxlePrismaticJoint = world.createJoint(axlePrismaticJointDef);

        axlePrismaticJointDef.initialize(main, rearAxle, rearAxle.getWorldCenter(), new Vector2(0,1));
        Joint rearAxlePrismaticJoint = world.createJoint(axlePrismaticJointDef);


    }

    // TODO: Cars Should just rotate the wheels.
    @Override
    public void gas() {
        super.gas();
    }

    @Override
    public void brake() {
        super.brake();
    }

}
