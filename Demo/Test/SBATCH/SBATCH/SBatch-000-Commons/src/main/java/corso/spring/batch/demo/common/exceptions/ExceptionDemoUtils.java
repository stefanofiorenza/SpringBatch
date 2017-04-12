package corso.spring.batch.demo.common.exceptions;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionDemoUtils {

		//1) Probability Exc Settings
		private static int retryFactor=5; 
		private static int skipThresHold=2; 
		
		//2) generator Settings
		private static int min=1;
		private static int max=100;
		private static Random random= new Random();
		
		public static  int generateNumber(){
			
			int generated= random.nextInt(max+1);//max inclusive		
			if(generated<min){
				generated=min;
			}
			return generated;
		}
		
		
		public static void evaluateExceptions(int itemN, Class clazz){		
			evaluateExceptions(String.valueOf(itemN),clazz);
		}
		
		public static void evaluateExceptions(String itemId, Class clazz){		
			
			int probNumber=generateNumber();
			
			if(probNumber%retryFactor==0){
				log.info(clazz.getName()+": test for n: "+itemId+" was failing RetryEx");
				throw new RetryFactorException("Generated: "+probNumber+
						" was divisible for RETRY value: "+retryFactor);
			}
		
			if(probNumber<skipThresHold){
				log.info(clazz.getName()+": test for n: "+itemId+" was failing SkipEx");
				throw new SkipThresholdException("Generated: "+probNumber+
						" was lower than SKIP threshold: "+skipThresHold);
			}
		}
		
		
		public static void throwRetriableException(String message){
			throw new RetryFactorException(message);
		}
		
		public static void throwSkippableException(String message){
			throw new SkipThresholdException(message);
		}
		
}
