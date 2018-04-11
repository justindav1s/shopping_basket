package org.jnd.product.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jnd.microservices.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;



@Component("ProductRepository")
@Profile("dev")
public class DefaultProductRepository extends RepositoryBase {

    private Log log = LogFactory.getLog(DefaultProductRepository.class);


    @Autowired
    private ProductCache cache;

    @PostConstruct
    public void init()  {

        log.debug("Setting up repository");

        getTypes().add(ProductType.FOOD.toString());
        getTypes().add(ProductType.CLOTHES.toString());
        getTypes().add(ProductType.GADGETS.toString());
        getProducts().putAll(cache.getFood());
        getProducts().putAll(cache.getClothes());
        getProducts().putAll(cache.getGadgets());

    }


}
