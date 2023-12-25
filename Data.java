/**
 * This class represents the records that will be stored in the HashDictionary.
 * @author chaejinhur
 *
 */
public class Data {
	private String config;
	private int score; 

	/**
	 * This constructor initializes a new Data object
	 * with the specified configuration and score.
	 * @param config
	 * @param score
	 */
	public Data(String config, int score) {
		this.config = config; 
		this.score = score; 
		
	}
	
	/**
	 * returns the configuration stored in this Data object 
	 * @return config
	 */
	public String getConfiguration() {
		return config; 
	}
	
	/**
	 * returns the score 
	 * @return score
	 */
	public int getScore() {
		return score; 
	}
	

}
