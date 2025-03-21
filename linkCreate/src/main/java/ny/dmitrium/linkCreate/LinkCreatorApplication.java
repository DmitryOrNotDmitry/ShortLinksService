package ny.dmitrium.linkCreate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class LinkCreatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkCreatorApplication.class, args);
	}

	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder.create()
				.type(org.postgresql.ds.PGSimpleDataSource.class)
				.url("jdbc:postgresql://localhost:5432/links_db")
				.username("postgres")
				.password("postgres")
				.build();
	}

}
