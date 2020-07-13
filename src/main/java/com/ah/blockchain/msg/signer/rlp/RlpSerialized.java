package com.ah.blockchain.msg.signer.rlp;

import org.web3j.crypto.Sign;
import org.web3j.rlp.RlpList;

/**
 * @Author: ahernandez
 * @Date: 7/7/20
 */
public interface RlpSerialized
{
	RlpList serialize(Sign.SignatureData signatureData);
}
