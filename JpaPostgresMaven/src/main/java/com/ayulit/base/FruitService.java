package com.ayulit.base;

import java.util.List;

public interface FruitService {
	
	// returns fruit (ROW) with id from table
	FruitORM findById (int id);
	
	// save fruit (ROW) to table
	void save(FruitORM fruit);
	
	// returns all fruits (ROWS) from table
	List<FruitORM> findAll();
	
}
