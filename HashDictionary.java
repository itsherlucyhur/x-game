/**
 * This class implements the Dictionary ADT using a hash table.
 * It implements the dictionary using a hash table with separate chaining.
 * @author chaejinhur
 *
 */

import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
	private LinkedList<Data>[] table; 		// array of linked lists to implement separate chaining
	private int size; 						// number of records in the dictionary
	private int capacity; 					// capacity of hash table 
	
	
	public HashDictionary(int size) {
		this.capacity = size; 
		this.size = 0; 
		this.table = new LinkedList[size];
		for(int i=0; i<size;i++) {
			this.table[i] = new LinkedList<>();
		}
	}
	
	/**
	 * This method adds record to the dictionary and throws a DictionaryException 
	 * if record.getConfiguration() is already in the dictionary
	 * @param record
	 * @return specific size at the position 
	 * @throws DictionaryException
	 */
	public int put (Data record) throws DictionaryException {
		int hash = hashFunction(record.getConfiguration(), this.capacity);
		
		if (table[hash] == null) {
			table[hash] = new LinkedList<>(); 
		}
		
		LinkedList<Data> list = table[hash]; 

		for (Data data: list) {
			// if configuration is already in the dictionary: 
			if (data.getConfiguration().equals(record.getConfiguration())) {
				throw new DictionaryException();
			}
		}
		list.add(record); 
		size++; 
		
		return table[hash].size() - 1; 
	}
	
	public void remove (String config) throws DictionaryException {
		int hash = hashFunction(config, capacity); 
		
		if (table[hash] == null) {
			throw new DictionaryException();
		}
		
		LinkedList<Data> list = table[hash];
		boolean found = false; 
		
		for (Data data : list) {
			if (data.getConfiguration().equals(config)) {
				list.remove(data);
				found = true; 
				size--; 
				break;
			}
		}
		if (!found) {
			throw new DictionaryException(); 
		}
		
		if (list.isEmpty()) {
			table[hash] = null; 
		}
	}
	
	public int get (String config) {
		int hash = hashFunction(config, capacity); 
		
		// when configuration isn't found in dictionary: 
		if(table[hash] == null) {
			return -1; 
		}
		
		LinkedList<Data> list = table[hash];
		
		for (Data data: list) {
			if (data.getConfiguration().equals(config)) {
				return data.getScore();
			}
		}
		return -1; 		// when configuration isn't found in the dictionary
	}
	
	/**
	 * returns the number of Data objects in the dictionary
	 * @return Size of Data objects
	 */
	public int numRecords() {
		return size; 
	}
	
	// Polynomial Hash Function  
	private int hashFunction(String config, int M) {	// M is the size 
		int hashValue = 0; 
		int length = config.length(); 
		
		for (int i = 0; i < length; i++) {
			char c = config.charAt(i); 
			hashValue = (hashValue * 39 + c) % M; 		// x value as prime number 
		}
		
		return hashValue; 
	}
	

}
