/****************************
Name    : Swathi Prakasha
****************************/


import static java.lang.Math.pow;

public class annuityCalculations 
{

    public static void main(String[] args) 
     {
      
       // Error message, if there is no argument passed
    	System.out.println("Hi from the new");
       if(args.length != 3)
        {
	   System.out.printf("Need 3 arguments, received 0\n");
	   System.exit(-1);
         }

       // reading command line arguments
       double annualAmount = Double.parseDouble(args[0]);
       double interestRate =  Double.parseDouble(args[1]);
       int numYears = Integer.parseInt(args[2]);

       double futVal=0, futValLoop =0,dueFutValLoop=0,dueFutVal=0;
  
       System.out.printf("----------------------------------------------------------------------------------\n");

       //Calls the function that calculates the future value of an ordinary annuity  & prints the results 
       futVal=ordinaryFutureValue(annualAmount, interestRate, numYears, true);
       futValLoop= ordinaryFutureValue(annualAmount, interestRate, numYears, false); 
       System.out.printf("\n\tBase \t\tRate \t# \tFormula \tLoop\n");
       System.out.printf("\t%.2f \t%.2f \t%d \t%.2f \t%.2f\n\n",annualAmount,interestRate,numYears,futVal,futValLoop);
       
       // calls the function that calculates the Present value of an ordinary annuity
       ordinaryPresentValue(annualAmount, interestRate, numYears);
       
       
       System.out.printf("----------------------------------------------------------------------------------\n");


       // calls the function that calculates the future value of an annuity due
       dueFutValLoop = dueFutureValue(annualAmount, interestRate, numYears, false);
       dueFutVal = dueFutureValue(annualAmount, interestRate, numYears, true);
       System.out.printf("\n\tBase \t\tRate \t# \tFormula \tLoop\n");
       System.out.printf("\t%.2f \t%.2f \t%d \t%.2f \t%.2f",annualAmount,interestRate,numYears,dueFutVal,dueFutValLoop);
 
       //calls the function that calculates the present value of an annuity due
       duePresentValue(annualAmount, interestRate, numYears);
      }


    
      /* This function calculates the future value for an ordinary annuity.
         The function takes the annula amount, interest, years and boolean value as paramter,and returns the calculated value
         if the boolean True, calculates the future value by formula, for a false boolean calculates by the loop */
       public static double  ordinaryFutureValue(double annualAmount, double interestRate, int numYears, boolean bool) 
        {
            double futureValue=0;


            // the following loop calculates the future value by formula for boolean value true       
            if (bool == true)
             {      
                futureValue = (annualAmount * (( Math.pow( (1+ interestRate) ,numYears) - 1) / interestRate ) ) ; 
             } 
           

            // the following loop calculates the future value by loop for boolean value false
           if (bool == false)
            {
               System.out.printf("Future value of an ordinary annuity is:\n");

                 for (int i=0; i<numYears; i++)
                 {
                    futureValue += annualAmount * Math.pow((1 + interestRate),i);  
                    System.out.printf("At the end of year %d, value is %.2f\n",(i+1), futureValue);
                 }

            }    
             return futureValue;
         } 


      /* This function calculates the present value for an ordinary annuity.
         The function takes the annula amount, interest, years as paramter,and prints the calculated value*/
       public static void ordinaryPresentValue(double annualAmount, double interestRate, int numYears)
        {
      
           double presentValue=0; 
           double presentValueLoop=0;

           //Calculting the present value by formula and printing the results
           presentValue = (annualAmount * ((1 - Math.pow((1+interestRate),-numYears)) / interestRate ) )  ;
           System.out.printf("The present Value of an Ordinary Annuity is  %f\n", presentValue);
           System.out.printf("That is the amount you have to start with to give out %.2f at the end\n", annualAmount);
           System.out.printf("of every year for %d years, assuming the remaining money earns %.2f\n\n", numYears, interestRate);

          
           //Loop to check the present value is correct or not, if correct the for the last year, the present value should be 0
           presentValueLoop = presentValue;
           System.out.printf("Starting with %f\n",presentValue);     
           for(int i=numYears,j=1; i>0; i--,j++)
            {
               presentValueLoop -= (annualAmount / (Math.pow((1 + interestRate), i) )) ;
               System.out.printf("At the end of the year %d, give out %.2f, leaving %.2f\n",j,annualAmount,presentValueLoop);

             }  
         } 
          


      /* This function calculates the future value for an annuity due.
         The function takes the annula amount, interest, years and boolean value as paramter,and returns the calculated value
         if the boolean True, calculates the future value by formula, for a false boolean calculates by the loop */
       public static double dueFutureValue(double annualAmount, double interestRate, int numYears, boolean bool)
        {
          
            double dueFutureValue =0;
            

             // the following loop calculates the future value by formula for boolean value true       
             if (bool == true)
              {

                dueFutureValue = (annualAmount * (( Math.pow( (1+ interestRate) ,numYears) - 1) / interestRate )*(1+interestRate) ) ;

              }  


          
            // the following loop calculates the future value by loop for boolean value false
            if (bool == false)
             {
                System.out.printf("\n");
                System.out.printf("Future Value of an Annuity Due:\n"); 

                  for (int i = 0 ; i < numYears; i++)
                   {

                      dueFutureValue +=( annualAmount * Math.pow((1 + interestRate),i));

                      System.out.printf("At the begining of year %d, value is %.2f\n", (i+1), dueFutureValue);

                   } 
                // These following 2 statements calculates the intetest acculmulated in the last year                   
                dueFutureValue +=( annualAmount * Math.pow((1 + interestRate),numYears));
                dueFutureValue -= annualAmount;
                System.out.printf("At the beginning of year %d,value is %.2f\n", (numYears+1), dueFutureValue); 
            }

            return dueFutureValue;
         }


      /* This function calculates the present value for an annuity due.
         The function takes the annula amount, interest, years as paramter,and prints the calculated value*/
       public static void duePresentValue(double annualAmount, double interestRate, int numYears)
        {

            double duePresentValue=0; 
            double duePresentValueLoop=0;
             
            //The following 2 lines of codes to calculate the present value of annuity due by formula and to print it
            duePresentValue = (annualAmount * ((1 - Math.pow((1+interestRate),-numYears)) / interestRate )*(1+interestRate) );
            System.out.printf("\n\nThe Present Value of an Annuity Due is %.2f\n", duePresentValue);
            
            
            System.out.printf("That is the amount you have to start with to give out %.2f at the begining\n", annualAmount);
            System.out.printf("of every year for %d years, assuming the remaining money earns %.2f\n\n", numYears, interestRate);
            System.out.printf("Starting with %.2f\n",duePresentValue);
            
            //The following loop is check wheather the value calculted above by formula is correct or not,
            duePresentValueLoop = duePresentValue;

            duePresentValueLoop -= annualAmount;

            System.out.printf("At the beginning of the year 1, give out %.2f, leaving %.2f\n",annualAmount,duePresentValueLoop);

            for(int i = (numYears-1),j =2; i>0 ; i--,j++) 
             {

               duePresentValueLoop -= (annualAmount / (Math.pow((1 + interestRate), i) )) ;
               System.out.printf("At the beginning of the year %d, give out %.2f, leaving %.2f\n",j,annualAmount,duePresentValueLoop);
             }

         } 

} 
