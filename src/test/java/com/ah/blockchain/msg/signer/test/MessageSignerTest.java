package com.ah.blockchain.msg.signer.test;

import com.ah.blockchain.msg.signer.MessageSigner;
import com.ah.blockchain.msg.signer.rlp.RlpSerialized;
import com.ah.blockchain.msg.signer.eip155.MessageSignerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.utils.Numeric;

/**
 * @Author: ahernandez
 * @Date: 7/7/20
 */
class MessageSignerTest
{
	private static MessageSigner messageSigner;

	private static Credentials credentials;

	private static long chainId;


	@BeforeAll
	public static void setUp () {
		messageSigner = new MessageSignerImpl();
		credentials = Credentials.create("0x4646464646464646464646464646464646464646464646464646464646464646");
		chainId = 1;
	}

	@Test
	void testSignature () {
		RlpSerialized transaction = TestFactory.buildMessage();

		Sign.SignatureData signatureData = messageSigner.sign(transaction, chainId, credentials);

		RlpList rlpList = transaction.serialize(signatureData);
		String message = Numeric.toHexString(RlpEncoder.encode(rlpList));


		Assertions.assertEquals(
				message,
				"0xf86c098504a817c800825208943535353535353535353535353535353535353535880" +
						"de0b6b3a76400008025a028ef61340bd939bc2195fe537567866003e1a15d" +
						"3c71ff63e1590620aa636276a067cbe9d8997f761aecb703304b3800ccf55" +
						"5c9f3dc64214b297fb1966a3b6d83");

	}

}
