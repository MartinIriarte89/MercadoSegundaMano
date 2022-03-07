package com.martiniriarte.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.martiniriarte.util.upload.StorageProperties;

@Configuration
@EnableJpaAuditing // Para que las fechas se asignen automaticamente en la bbdd 
@EnableConfigurationProperties(StorageProperties.class) // para asignar las propiedades
														// de carga de imagenes
public class AuditConfig {

}
