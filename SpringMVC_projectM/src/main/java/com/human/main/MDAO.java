package com.human.main;

import java.util.ArrayList;

public interface MDAO {
	public void inputM(MDTO mdto);

	public ArrayList<MDTO> selectM();

	public MDTO selectOne(String id);

	public void updateOne(MDTO mdto);

	public void deleteOne(MDTO mdto);
}
