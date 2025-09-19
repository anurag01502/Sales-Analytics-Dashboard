package in.sp.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.repository.OrdersRepository;

@RestController
@CrossOrigin(origins = "*") // Enable CORS for frontend
public class DashboardController {

    @Autowired
    private OrdersRepository ordersRepository;

    // 1. Total Revenue
    @GetMapping("/get-api/dashboard/total-revenue")
    public Double getTotalRevenue() {
        return ordersRepository.getTotalRevenue();
    }

    // 2. Average Sales Per Sales Rep (across all reps)
    @GetMapping("/get-api/dashboard/average-sales-per-rep")
    public Double getAverageSalesPerRep() {
        List<Double> averages = ordersRepository.getAverageSalesPerRep();
        return averages.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    // 3. Most Sold Product (top 1 by frequency)
    @GetMapping("/get-api/dashboard/most-sold-product")
    public String getMostSoldProduct() {
        List<String> products = ordersRepository.getMostSoldProduct(PageRequest.of(0, 1));
        return products.isEmpty() ? "No Data" : products.get(0);
    }

    // 4. Best Performing Salesperson (top 1 by revenue)
    @GetMapping("/get-api/dashboard/best-sales-person")
    public String getBestSalesPerson() {
        List<String> names = ordersRepository.getBestSalesPerson(PageRequest.of(0, 1));
        return names.isEmpty() ? "No Data" : names.get(0);
    }
    
    @GetMapping("/get-api/dashboard/stats")
    public DashboardStats getDashboardStats() {
        Double totalRevenue = ordersRepository.getTotalRevenue();

        List<Double> averages = ordersRepository.getAverageSalesPerRep();
        Double averageSalesPerRep = averages.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        List<String> mostSoldProducts = ordersRepository.getMostSoldProduct(PageRequest.of(0, 1));
        String mostSoldProduct = mostSoldProducts.isEmpty() ? "No Data" : mostSoldProducts.get(0);

        List<String> bestSalesPersons = ordersRepository.getBestSalesPerson(PageRequest.of(0, 1));
        String bestSalesPerson = bestSalesPersons.isEmpty() ? "No Data" : bestSalesPersons.get(0);

        return new DashboardStats(totalRevenue, averageSalesPerRep, mostSoldProduct, bestSalesPerson);
    }

    public static class DashboardStats {
        private Double totalRevenue;
        private Double averageSalesPerRep;
        private String mostSoldProduct;
        private String bestSalesPerson;

        public DashboardStats(Double totalRevenue, Double averageSalesPerRep, String mostSoldProduct, String bestSalesPerson) {
            this.totalRevenue = totalRevenue;
            this.averageSalesPerRep = averageSalesPerRep;
            this.mostSoldProduct = mostSoldProduct;
            this.bestSalesPerson = bestSalesPerson;
        }
        
        // Getters (and setters if needed)
        public Double getTotalRevenue() { return totalRevenue; }
        public Double getAverageSalesPerRep() { return averageSalesPerRep; }
        public String getMostSoldProduct() { return mostSoldProduct; }
        public String getBestSalesPerson() { return bestSalesPerson; }
    }

}

