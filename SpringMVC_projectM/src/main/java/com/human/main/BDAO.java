package com.human.main;

import java.util.ArrayList;

public interface BDAO {

	public void inputB(BDTO bdto);

	public ArrayList<BDTO> selectB();

	public ArrayList<BDTO> detailN(BDTO bdto);

	public void updateOne(MDTO mdto);

	public void deleteOne(MDTO mdto);
}
