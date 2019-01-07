package com.kunal;

import com.badlogic.gdx.physics.box2d.Body;

public class AllVariables {

    public static final float forDesktop = .5f;

    //variable that shows stunt step
    // every move is divided in steps
    public static byte stuntStep = 1;

    //should move to next step
    public static boolean shouldmoveToNextStep = false;

    //this varibale shows if the balancing should be applied or not
    public static boolean BalancingPlayer = true;

    //movement names that is turned true if the stunt is in process
    // else it is false
    public static boolean BackFlip = false;

    public static Body tester , tester2;
    //whole character
    //left part is part 1 and right is part 2
    //1 is for lower , 2 is upper than 1 and so on
    public static Body Front_foot1, Front_foot2, Front_leg, Front_Thai, Back_foot1, Back_foot2, Back_leg, Back_Thai,
            Stomach1, Stomach2, Stomach3, neck, head, Front_arm, Back_arm, Front_hand, Back_hand;

    //the current status of movement
    public static String MovementStats = "none";


    // Body variables
    // the true status shows balancing is applied
    //false shows no balancing
    public static boolean head_Balance = true, neck_Balance = true, stomach3_Balance = true, stomach2_Balance = true, stomach1_Balance = true;
    public static boolean Front_foot1_Balance = true, Front_foot2_Balance = true, Front_leg_Balance = true, Front_Thai_Balance = true, Front_arm_Balane = true, Front_hand_Balance = true;
    public static boolean Back_foot1_Balance = true, Back_foot2_Balance = true, Back_leg_Balance = true, Back_Thai_Balance = true, Back_arm_Balane = true, Back_hand_Balance = true;


}
