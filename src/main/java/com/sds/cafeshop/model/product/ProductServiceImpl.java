package com.sds.cafeshop.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.cafeshop.domain.Product;
import com.sds.cafeshop.domain.Psize;
import com.sds.cafeshop.exception.ProductException;
import com.sds.cafeshop.exception.PsizeException;
import com.sds.cafeshop.exception.UploadException;

@Service
public class ProductServiceImpl implements ProductService {

//    @Autowired
//    private FileManager fileManager; // 파일 업로드 담당 객체
    
    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductDAO productDAO; // 상품 관련 DAO

    @Autowired
    private PsizeDAO psizeDAO; // 사이즈 관련 DAO

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ProductException.class, PsizeException.class, UploadException.class})
    public void regist(Product product) throws ProductException, PsizeException, UploadException {
        try {
            // 1) 파일 업로드
            imageService.save(product);

            // 2) 상품 등록
            productDAO.insert(product); // 이후 product 객체에 자동으로 pk가 채워짐

            // 3) 사이즈 등록
            for (Psize psize : product.getPsizeList()) {
                psize.setProduct(product); // 상품 정보를 설정
                psizeDAO.insert(psize); // 사이즈 정보 등록
            }
        } catch (Exception e) {
            // 롤백을 위한 예외 처리
            throw new ProductException("상품 등록에 실패하였습니다.", e);
        }
    }

    @Override
    public List<Product> selectAll() {
        return productDAO.selectAll();
    }

    @Override
    public List<Product> selectAllByTopIdx(int topcategory_idx) {
        return productDAO.selectAllByTopIdx(topcategory_idx);
    }

    @Override
    public List<Product> selectAllBySubIdx(int subcategory_idx) {
        return productDAO.selectAllBySubIdx(subcategory_idx);
    }

    @Override
    public Product select(int product_idx) {
        return productDAO.select(product_idx);
    }

    @Override
    public void update(Product product) {
        // TODO: 상품 업데이트 로직 구현
    }

    @Override
    public void delete(Product product) {
        // TODO: 상품 삭제 로직 구현
    }
}
