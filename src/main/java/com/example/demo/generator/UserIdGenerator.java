package com.example.demo.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class UserIdGenerator extends SequenceStyleGenerator {

    public static final String PREFIX_PARAMETER = "prefixValue";
    public static final String DEFAULT_PREFIX = "";

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String DEFAULT_NUMBER_FORMAT = "%d";

    private String prefixValue;
    private String numberFormat;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        prefixValue = ConfigurationHelper.getString(PREFIX_PARAMETER, params, DEFAULT_PREFIX);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, DEFAULT_NUMBER_FORMAT);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return prefixValue + String.format(numberFormat, super.generate(session, object));
    }

}
