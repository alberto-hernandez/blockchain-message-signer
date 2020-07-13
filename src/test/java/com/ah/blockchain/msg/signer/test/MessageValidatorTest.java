package com.ah.blockchain.msg.signer.test;

import com.ah.blockchain.msg.signer.MessageSigner;
import com.ah.blockchain.msg.signer.eip155.MessageValidatorImpl;
import com.ah.blockchain.msg.signer.rlp.RlpSerialized;
import com.ah.blockchain.msg.signer.MessageValidator;
import com.ah.blockchain.msg.signer.eip155.MessageSignerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.crypto.*;

/**
 * @Author: ahernandez
 * @Date: 7/7/20
 */
class MessageValidatorTest
{

	private static MessageValidator messageValidator;

	private static MessageSigner messageSigner;

	private static Credentials credentials;

	private static long chainId;

	private static String address;

	@BeforeEach
	public void setUp() throws Exception
	{
		messageSigner = new MessageSignerImpl();
		messageValidator = new MessageValidatorImpl();
		credentials = Credentials.create("0x4646464646464646464646464646464646464646464646464646464646464646");
		chainId = 1;
		address = "0x9d8a62f656a8d1615c1294fd71e9cfb3e4855a4f";
	}


	@Test
	void testValidation() throws Exception
	{
		RlpSerialized transaction = TestFactory.buildMessage();

		Sign.SignatureData signatureData = messageSigner.sign(transaction, chainId, credentials);

		boolean validated = messageValidator.validate(transaction, signatureData, address, chainId);
		Assertions.assertTrue(validated, "Message is not validated");
	}


}
