package net.anotheria.marsnews.shared;

public class GovermentTypeHelper {
	public static final String MONARCHY_DESC = "Monarchy";
	public static final String DEMOCRACY_DESC = "Democracy";
	public static final String REPUBLIC_DESC = "Republic";
	public static final String THEOCRACY_DESC = "Theocracy";
	public static final String COMMUNISM_DESC = "Communism";
	public static final String DICTATORSHIP_DESC = "Dictatorship"; 	
	public static final String TYRANNY_DESC = "Tyranny"; 	
	public static final String FASCISM_DESC = "Fascism";	

	public static final int MONARCHY = 0;
	public static final int DEMOCRACY = 1;
	public static final int REPUBLIC = 2;
	public static final int THEOCRACY = 3;
	public static final int COMMUNISM = 4;
	public static final int DICTATORSHIP = 5; 	
	public static final int TYRANNY = 6; 	
	public static final int FASCISM = 7;
	
	public static final char MONARCHY_CHAR =  'M';
	public static final char DEMOCRACY_CHAR = 'D';
	public static final char REPUBLIC_CHAR =  'R';
	public static final char THEOCRACY_CHAR = 'H';
	public static final char COMMUNISM_CHAR = 'C';
	public static final char DICTATORSHIP_CHAR = 'I'; 	
	public static final char TYRANNY_CHAR = 'T'; 	
	public static final char FASCISM_CHAR = 'F';
	
	public static final String DESC[] = {
		MONARCHY_DESC,
		DEMOCRACY_DESC,
		REPUBLIC_DESC,
		THEOCRACY_DESC,
		COMMUNISM_DESC,
		DICTATORSHIP_DESC, 	
		TYRANNY_DESC, 	
		FASCISM_DESC,	
	};
	
	public static final int[] TYPES = {
		MONARCHY,
		DEMOCRACY,
		REPUBLIC,
		THEOCRACY,
		COMMUNISM,
		DICTATORSHIP,
		TYRANNY,
		FASCISM
	};
	
	public static final String int2string(int c){
		return DESC[c];
	}
	
	public static int char2int(char c){
		switch(c){
		case  MONARCHY_CHAR :
			return MONARCHY;
		case  DEMOCRACY_CHAR :
			return DEMOCRACY;
		case  REPUBLIC_CHAR :
			return REPUBLIC;
		case  THEOCRACY_CHAR :
			return THEOCRACY;
		case  COMMUNISM_CHAR: 
			return COMMUNISM;
		case  DICTATORSHIP_CHAR :
			return DICTATORSHIP;
		case  TYRANNY_CHAR : 	
			return TYRANNY;
		case  FASCISM_CHAR :
			return FASCISM;
		
		}
		throw new RuntimeException("Unsupported goverment type: "+c);
	}

}
