package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDAO {
    void insert(Seller seller);
    void updateById(Integer id, Seller updatedSeller);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findAll();
}
