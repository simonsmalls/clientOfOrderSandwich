package be.abis.exjunit;


import be.abis.exjunit.service.PaymentService;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({AddressTest.class,PersonTest.class, PaymentService.class})

public class TestSuite {

}
