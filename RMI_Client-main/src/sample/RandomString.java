package sample;

import java.util.Random;

public class RandomString {
    // Random number
    private static final String CHAR_LIST = "0123456789";

	/*
	Use the below String to random chars, numbers or both.
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String CHAR_LIST = "1234567890";
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	*/


    private static final int RANDOM_STRING_LENGTH = 15;

    public String generateRandomString()
    {
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++)
        {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }


    private int getRandomNumber()
    {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1)
        {
            return randomInt;
        }
        else
        {
            return randomInt - 1;
        }
    }


    public static void main(String[] args) {
        RandomString ramdomNumber = new RandomString();
        System.out.println(ramdomNumber.generateRandomString());
        // System.out.println(ramdomEmail.generateRandomString()+"@sangbui.com");
    }
}
