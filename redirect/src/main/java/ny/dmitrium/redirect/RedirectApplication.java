package ny.dmitrium.redirect;

import ny.dmitrium.redirect.config.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@SpringBootApplication
public class RedirectApplication {

	@Autowired
	private DBConfig dbConfig;

	public static void main(String[] args) {
		SpringApplication.run(RedirectApplication.class, args);
	}

	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder.create()
				.type(org.postgresql.ds.PGSimpleDataSource.class)
				.url(dbConfig.getUrl())
				.username("postgres")
				.password("postgres")
				.build();
	}

}
