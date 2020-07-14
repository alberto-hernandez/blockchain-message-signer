package com.github.ah.blockchain.msg.signer;

import com.github.ah.blockchain.msg.signer.rlp.RlpSerialized;
import org.web3j.crypto.Sign;

/**
 * Message Validator Interface in order to verify the signature of the message and the Address
 *
 * @author: Alberto Hernández
 * @date: 8/7/20
 */
public interface MessageValidator
{

	/**
	 * Returns True if the singature was done for the Chain Id and the Address
	 * @param rlpSerialized Object to check the signature
	 * @param signatureData Signature to verify
	 * @param address Address of to verify the signer
	 * @param chainId Chain Id of the Verification
	 * @return true in case of the signature and address is correct
	 */
	boolean validate (RlpSerialized rlpSerialized, Sign.SignatureData signatureData, String address, long chainId);
}
