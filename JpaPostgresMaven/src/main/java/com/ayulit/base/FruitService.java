package com.ayulit.base;

import java.util.List;

public interface FruitService {
	
	FruitORM findById (int id);
	
	void save(FruitORM fruit);
	
	// returns all fruits (ROWS) from table
	List<FruitORM> findAll();
	
}
