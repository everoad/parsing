package parsing;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import parsing.service.ParsingService;
import parsing.view.MainView;
import parsing.vo.BoardVO;
import parsing.vo.LteVO;

@MapperScan(value = { "parsing.mapper" })
@SpringBootApplication
public class ParsingApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ParsingApplication.class).headless(false)
				.run(args);

		ParsingService ps = ctx.getBean(ParsingService.class);

		ps.parsingExcelFile();

		MainView view = ctx.getBean(MainView.class);
		view.setVisible(true);

	}

	@Bean
	public ParsingService parsingSerivce() {
		return new ParsingService();
	}

	@Bean
	public MainView mainView() {
		return new MainView();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(datasource);

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
		sqlSessionFactoryBean.setTypeAliases(new Class[] { BoardVO.class, LteVO.class });

		return sqlSessionFactoryBean.getObject();

	}
}
