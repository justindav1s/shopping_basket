package org.jnd.microservices.apigateway.controller;

import org.jnd.microservices.model.Basket;
import org.jnd.microservices.model.User;
import org.jnd.microservices.apigateway.proxies.BasketProxy;
import org.jnd.microservices.apigateway.proxies.InventoryProxy;
import org.jnd.microservices.apigateway.proxies.UserProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Retry;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * ${environment.user_backend}/basket/login POST
 * ${environment.user_backend}/basket/logout DELETE
 * ${environment.basket_backend}/basket/get/${basket.id} GET
 * ${environment.basket_backend}/basket/add/${product.id} PUT
 * ${environment.basket_backend}/basket/remove/${itemAtIndex} DELETE
 * ${environment.inventory_backend}/products/types GET
 * ${environment.inventory_backend}/products/all GET
 * ${environment.inventory_backend}/products/type/${type} GET
 */

@CrossOrigin
@RestController
@RequestMapping("/amazin")
public class GatewayController {

    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);

    @Autowired
    private UserProxy userProxy;

    @Autowired
    private InventoryProxy inventoryProxy;

    @Autowired
    private BasketProxy basketProxy;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<String> login(@RequestBody User user, @RequestHeader HttpHeaders headers) {
        
        ResponseEntity<String> response = userProxy.login(user, headers);
        return response;
    }

    @RequestMapping(value = "/loginhealth", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<String> loginhealth(@RequestHeader HttpHeaders headers) {
        
        ResponseEntity<String> response = userProxy.getLoginHealth(headers);
        
        return response;
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<String> session(@RequestBody User user, @RequestHeader HttpHeaders headers) {

        ResponseEntity<String> response = userProxy.login(user, headers);
        return response;
    }

    @RequestMapping(value = "/logout/{id}", method = RequestMethod.DELETE, produces = "application/json")
    ResponseEntity<?> logout(@PathVariable int id, @RequestHeader HttpHeaders headers) {

        String response = userProxy.logout(id, headers);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/basket/get/{basketid}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> getBasket(@PathVariable int basketid, @RequestHeader HttpHeaders headers) {
        ResponseEntity<Basket> response = basketProxy.getBasket(basketid, headers);
        return response;
    }

    @RequestMapping(value = "/basket/{basketid}/add/{productid}", method = RequestMethod.PUT, produces = "application/json")
    ResponseEntity<?> addToBasket(@PathVariable int basketid, @PathVariable int productid, @RequestHeader HttpHeaders headers) {

        ResponseEntity<Basket> response = basketProxy.addToBasket(basketid, productid, headers);
        return response;
    }

    @RequestMapping(value = "/basket/{basketid}/remove/{itemAtIndex}", method = RequestMethod.DELETE, produces = "application/json")
    ResponseEntity<?> removeFromBasket(@PathVariable int basketid, @PathVariable int itemAtIndex, @RequestHeader HttpHeaders headers) {

        ResponseEntity<Basket> response = basketProxy.removefromBasket(basketid, itemAtIndex, headers);
        return response;
    }

    @RequestMapping(value = "/basket/{basketid}/empty", method = RequestMethod.DELETE, produces = "application/json")
    ResponseEntity<?> emptyBasket(@PathVariable int basketid, @RequestHeader HttpHeaders headers) {

        ResponseEntity<Basket> response = basketProxy.emptyBasket(basketid, headers);
        return response;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> getProduct(@PathVariable int id, @RequestHeader HttpHeaders headers) {

        ResponseEntity<?> response = inventoryProxy.getProduct(String.valueOf(id), null);
        return response;

    }

    @RequestMapping(value = "/products/types", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> getProductTypes(@RequestHeader HttpHeaders headers) {

        ResponseEntity<?> response = inventoryProxy.getProductTypes(null);
        return response;
    }

    @RequestMapping(value = "/products/all", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> getAllProducts(@RequestHeader HttpHeaders headers) {

        ResponseEntity<?> response = inventoryProxy.getAllProducts(null);
        return response;
    }

    @RequestMapping(value = "/products/type/{type}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> getAllProductsOfType(@PathVariable String type, @RequestHeader HttpHeaders headers) {

        ResponseEntity<?> response = inventoryProxy.getProductsofType(type, null);
        return response;
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String ping() {
        return "OK";
    }


}
