package com.ah.blockchain.msg.signer;

import com.ah.blockchain.msg.signer.rlp.RlpSerialized;
import org.web3j.crypto.Sign;

/**
 * @Author: ahernandez
 * @Date: 8/7/20
 */
public interface MessageValidator
{

	boolean validate (RlpSerialized rlpSerialized, Sign.SignatureData signatureData, String address, long chainId);
}
