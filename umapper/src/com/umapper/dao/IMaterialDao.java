package com.umapper.dao;

import com.umapper.po.Material;

public interface IMaterialDao {

	public Material getMaterial(String url);
	public void addMaterial(Material material);
	public void deleteMaterial(String url);
}
