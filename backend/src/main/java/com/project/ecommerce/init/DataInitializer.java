package com.project.ecommerce.init;

import com.project.ecommerce.entity.Command;
import com.project.ecommerce.entity.OrderLine;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.repository.CommandRepository;
import com.project.ecommerce.repository.OrderLineRepository;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommandRepository commandRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si les données existent déjà
        if (userRepository.count() > 0 ) {
            System.out.println("Les données existent déjà, initialisation ignorée.");
            return;
        }
        System.out.println("Initialisation des données de test...");
        // Création des données de test
        User user = new User();
        user.setLastName("first");
        user.setFirstName("user");
        user.setAddress("23 avenue de Clemence - 75000 Paris");
        user.setEmail("f.user@example.com");
        user.setPhoneNumber("0003050412");
        user.setUsername("fuser");
        user.setPassword(passwordEncoder.encode("fu1234@#"));
        user.setRole("USER");

        userRepository.save(user);

        User admin = new User();
        admin.setLastName("first");
        admin.setFirstName("admin");
        admin.setAddress("1 rue de la Fleur - 69000 Lyon");
        admin.setEmail("f.admin@example.com");
        admin.setPhoneNumber("0018547299");
        admin.setUsername("fadmin");
        admin.setPassword(passwordEncoder.encode("fa1234@#"));
        admin.setRole("ADMIN");

        userRepository.save(admin);

        Product product1 = new Product();
        product1.setName("Tisane fraîcheur");
        product1.setDescription("- Menthe \n- Citron\n- Verveine");
        product1.setImage("url1");
        product1.setPrice(5.00);
        product1.setQuantity(10);

        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Tisane fruitée");
        product2.setDescription("- Mûre \n- Orange\n- Pomme");
        product2.setImage("url2");
        product2.setPrice(3.00);
        product2.setQuantity(20);

        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Tisane Sommeil");
        product3.setDescription("- Camomille \n- Miel\n- Verveine");
        product3.setImage("url3");
        product3.setPrice(2.50);
        product3.setQuantity(10);

        productRepository.save(product3);

        Command command1 = new Command();
        command1.setDate(LocalDate.now());
        command1.setPaymentMode("CB");
        command1.setUser(user);

        commandRepository.save(command1);

        List<OrderLine> orderLineList1 = new ArrayList<>();
        OrderLine orderLine1 = new OrderLine();
        orderLine1.setProduct(product3);
        orderLine1.setPrice(5.00);
        orderLine1.setQuantity(2);
        orderLine1.setCommand(command1);

        orderLineRepository.save(orderLine1);

        orderLineList1.add(orderLine1);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setProduct(product2);
        orderLine2.setPrice(9.00);
        orderLine2.setQuantity(3);
        orderLine2.setCommand(command1);

        orderLineRepository.save(orderLine2);

        orderLineList1.add(orderLine2);

        OrderLine orderLine3 = new OrderLine();
        orderLine3.setProduct(product1);
        orderLine3.setPrice(5.00);
        orderLine3.setQuantity(1);
        orderLine3.setCommand(command1);

        orderLineRepository.save(orderLine3);

        orderLineList1.add(orderLine3);

        System.out.println("Les données sont ajoutées.");
    }
}
