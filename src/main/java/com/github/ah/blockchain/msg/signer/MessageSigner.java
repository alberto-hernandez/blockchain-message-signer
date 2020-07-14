package com.github.ah.blockchain.msg.signer;

import com.github.ah.blockchain.msg.signer.rlp.RlpSerialized;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;

/**
 * Message Signer Interface in order to generate a SignatureData for a determined chainId and for a Credentials.
 *
 * @author: Alberto Hernández
 * @date: 6/7/20
 */
public interface MessageSigner
{

	/**
	 *
	 * @param rlpSerialized Object to Sign
	 * @param chainId Chain Id of the chain
	 * @param credentials Credentials to sign the Object
	 * @return Signature ECDSA generated
	 */
	Sign.SignatureData sign(RlpSerialized rlpSerialized, long chainId, Credentials credentials);

}