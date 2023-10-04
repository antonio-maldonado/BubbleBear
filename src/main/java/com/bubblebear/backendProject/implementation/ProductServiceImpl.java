package com.bubblebear.backendProject.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bubblebear.backendProject.entity.Product;
import com.bubblebear.backendProject.repository.ProductRepository;
import com.bubblebear.backendProject.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product createProduct(Product producto) {
		producto.setId(null);
		
		return saveProduct(producto);
	}

	@Override
	public Product saveProduct(Product producto) {
		return productRepository.save(producto);
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).
				orElseThrow( ()-> new IllegalStateException("Product does not exist with id "+ id) );
	}

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product updateProduct(Product producto, Long id) {
		Product existingProduct = productRepository.findById(id).
				orElseThrow( ()-> new IllegalStateException("Product does not exist with id "+ id) );

		existingProduct.setName(producto.getName());
		existingProduct.setPrice(producto.getPrice());
		existingProduct.setSize(producto.getSize());
		existingProduct.setStock(producto.getStock());
		existingProduct.setHide(producto.getHide());
		existingProduct.setDescription(producto.getDescription());
		existingProduct.setPhoto(producto.getPhoto());
		existingProduct.setCategory(producto.getCategory());
		existingProduct.setFlavor(producto.getFlavor());
		
		return productRepository.save(existingProduct);
	}

	@Override
	public void deleteProduct(Long id) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow( ()-> new IllegalStateException("Product does not exist with id "+ id) );
		productRepository.delete(existingProduct);
	}

}
