package com.ah.blockchain.msg.signer.eip155;

import com.ah.blockchain.msg.signer.rlp.RlpSerialized;
import com.ah.blockchain.msg.signer.MessageValidator;
import com.ah.blockchain.msg.signer.helper.MessageHelper;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

/**
 * @Author: ahernandez
 * @Date: 8/7/20
 */
public class MessageValidatorImpl implements MessageValidator
{
	@Override
	public boolean validate(RlpSerialized rlpSerialized, Sign.SignatureData signatureData, String address, long chainId)
	{
		Sign.SignatureData signatureWEIP = withoutEIP155(signatureData, chainId);

		if (! validateChainID(signatureWEIP)) {
			return false;
		}

		try
		{
			byte[] transactionMessage = MessageHelper.encode(rlpSerialized, chainId);
			byte[] msgHash = Hash.sha3(transactionMessage);

			BigInteger publicKey = Sign.signedMessageHashToKey(msgHash, signatureWEIP);
			return address.equals("0x" + Keys.getAddress(publicKey));
		} catch (Exception ex) {
			return false;
		}
	}


	private Sign.SignatureData withoutEIP155 (Sign.SignatureData signatureData, long chainId)
	{
		long eip155 = Numeric.toBigInt(signatureData.getV()).longValue();
		long offset = (chainId * 2) + 35;
		long value = eip155 - offset + 27;

		return new Sign.SignatureData(BigInteger.valueOf(value).toByteArray(), signatureData.getR(), signatureData.getS());
	}

	private boolean validateChainID (Sign.SignatureData signatureData) {
		long v = Numeric.toBigInt(signatureData.getV()).longValue();
		return v == 27L || v == 28L;
	}

}
