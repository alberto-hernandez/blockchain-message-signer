package com.ah.blockchain.msg.signer.eip155;

import com.ah.blockchain.msg.signer.MessageSigner;
import com.ah.blockchain.msg.signer.helper.MessageHelper;
import com.ah.blockchain.msg.signer.rlp.RlpSerialized;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;
import org.web3j.crypto.TransactionEncoder;

/**
 * @Author: ahernandez
 * @Date: 6/7/20
 */
public class MessageSignerImpl implements MessageSigner
{
	@Override
	public Sign.SignatureData sign(RlpSerialized rlpSerialized, long chainId, Credentials credentials)
	{
		byte[] encodedTransaction = MessageHelper.encode(rlpSerialized, chainId);
		Sign.SignatureData signatureData = Sign.signMessage(encodedTransaction, credentials.getEcKeyPair());
		return TransactionEncoder.createEip155SignatureData(signatureData, chainId);
	}


}
