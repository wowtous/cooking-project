package org.darebeat;

import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.Phonenumber;
import org.darebeat.utils.IdCardUtil;
import org.darebeat.utils.MobileUtil;

import java.util.Locale;

/**
 * Created by darebeat on 4/26/17.
 */
public class utilTest {
    public static void main(String[] args) {
        System.out.println(IdCardUtil.conver15CardTo18("130503670401001"));
        System.out.println(MobileUtil.getCarrier("13576538736","86"));
        System.out.println(MobileUtil.getGeo("13576538736","86"));

        Phonenumber.PhoneNumber swissMobileNumber =
                new Phonenumber.PhoneNumber().setCountryCode(41).setNationalNumber(798765432L);
        PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();
// Outputs "Swisscom"
        System.out.println(carrierMapper.getNameForNumber(swissMobileNumber, Locale.ENGLISH));
    }
}
