package in.sp.main;

import org.springframework.boot.SpringApplication;

public class TestSalesPerfomanceAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.from(SalesPerfomanceAnalyticsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
