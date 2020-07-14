package com.github.ah.blockchain.msg.signer.rlp;

import org.web3j.crypto.Sign;
import org.web3j.rlp.RlpList;

import java.util.Optional;

/**
 * An Object to Implement to generate a Signature.
 *
 * The optional parameter signature is assigned in order to include a signature in the serialization.
 *
 * @author: Alberto Hernández
 * @date: 7/7/20
 */
public interface RlpSerialized
{
	/**
	 * Implements the serialization of the object
	 * @param signatureData Optional signature in case that it wants to be included in the serialization
	 * @return The RlpList finished
	 */
	RlpList serialize(Sign.SignatureData signatureData);
}
