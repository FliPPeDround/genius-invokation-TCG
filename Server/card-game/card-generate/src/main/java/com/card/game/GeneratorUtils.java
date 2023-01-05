package com.card.game;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.*;
import java.util.function.Consumer;

/**
 * MyBatisPlus代码生成器（新），基本满足使用了。
 *
 * @author tomyou
 */
public class GeneratorUtils {

    private static final String CURRENT_MAPPER_DIR = System.getProperty("user.dir") + "/src/main/resources/mapper";
    private static final String CURRENT_DIR = System.getProperty("user.dir") + "/src/main/java";

    private static String author;

    private static String packageName;

    private static String tablePrefix;

    private static List<String> tableNames;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入作者名称: ");
        author = scanner.nextLine();
        System.out.println("请输入包名:");
        packageName = scanner.nextLine();
        System.out.println("请输入需要去掉表的前缀: (没有则不输入)");
        tablePrefix =scanner.nextLine();
        System.out.println("请输入表名，多个英文逗号分隔？所有输入 all");
        tableNames = getTables(scanner.nextLine());

        runCodeGenerator();

    }

    public static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    /**
     * 快速生成
     */
    private static void runCodeGenerator() {
        FastAutoGenerator.create(getDataSourceConfig())
                .globalConfig(getGlobalConfig())
                .packageConfig(getPackageConfig())
                .strategyConfig(getStrategyConfig())
                .execute();

    }


    /**
     * 数据库配置(DataSourceConfig)
     */
    private static DataSourceConfig.Builder getDataSourceConfig() {
        ResourceBundle datasource = ResourceBundle.getBundle("datasource");
        String url = datasource.getString("jdbc.url");
        String username = datasource.getString("jdbc.username");
        String password = datasource.getString("jdbc.password");
        return new DataSourceConfig.Builder(url, username, password);
    }

    /**
     * 全局配置(GlobalConfig)
     */
    private static Consumer<GlobalConfig.Builder> getGlobalConfig() {
        return builder -> builder
                .enableSwagger()
                .author(author)
                .outputDir(CURRENT_DIR)
                .fileOverride()
                .build();
    }

    /**
     * 包配置(PackageConfig)
     */
    private static Consumer<PackageConfig.Builder> getPackageConfig() {
        return builder -> builder
                //.moduleName("sysxxx")
                .parent(packageName)
                .pathInfo(Collections.singletonMap(OutputFile.xml, CURRENT_MAPPER_DIR))
                .entity("pojo.entity")
                .service("service")
                .serviceImpl("service.impl")
                .controller("controller")
                .build();
    }

    /**
     * 策略配置(StrategyConfig)
     */
    private static Consumer<StrategyConfig.Builder> getStrategyConfig() {
        return builder -> {

            builder.addTablePrefix(tablePrefix)
                    .addInclude(tableNames)
                    .build();

            //Entity 策略配置
            builder.entityBuilder()
                    .enableLombok()
                    .idType(IdType.AUTO)
                    .formatFileName("%sEntity")
                    .addTableFills(new Column("create_time", FieldFill.INSERT_UPDATE))
                    .addTableFills(new Column("update_time", FieldFill.INSERT))
                    .logicDeleteColumnName("is_deleted")
                    .build();

            //Mapper 策略配置
            builder.mapperBuilder()
                    .enableBaseResultMap()
                    .enableBaseColumnList()
                    .build();

            //Service 策略配置
            builder.serviceBuilder()
                    .formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImpl")
                    .build();

            //Controller 策略配置
            builder.controllerBuilder()
                    .enableRestStyle()
                    .formatFileName("%sController")
                    .build();
        };
    }

}
