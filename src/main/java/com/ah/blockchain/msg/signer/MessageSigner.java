package com.ah.blockchain.msg.signer;

import com.ah.blockchain.msg.signer.rlp.RlpSerialized;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;

/**
 * @Author: ahernandez
 * @Date: 6/7/20
 */
public interface MessageSigner
{

	Sign.SignatureData sign(RlpSerialized rlpSerialized, long chainID, Credentials credentials);

}