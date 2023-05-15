package com.zxcv5595.cms.order.service;

import com.zxcv5595.cms.order.domain.model.Product;
import com.zxcv5595.cms.order.domain.model.ProductItem;
import com.zxcv5595.cms.order.domain.product.AddProductItemForm;
import com.zxcv5595.cms.order.domain.repository.ProductItemRepository;
import com.zxcv5595.cms.order.domain.repository.ProductRepository;
import com.zxcv5595.cms.order.exception.CustomException;
import com.zxcv5595.cms.order.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductItemService {

    private final ProductRepository productRepository;

    public Product addProductItem(Long sellerId, AddProductItemForm form) {
        Product product = productRepository.findBySellerIdAndId(sellerId, form.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
        if (product.getProductItems().stream()
                .anyMatch(item -> item.getName().equals(form.getName()))) {
            throw new CustomException(ErrorCode.SAME_ITEM_NAME);
        }

        ProductItem productItem = ProductItem.of(sellerId,form);
        product.getProductItems().add(productItem);
        return product;
    }
}
