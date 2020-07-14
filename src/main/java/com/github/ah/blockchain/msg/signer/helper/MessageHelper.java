package com.github.ah.blockchain.msg.signer.helper;

import com.github.ah.blockchain.msg.signer.rlp.RlpSerialized;
import org.web3j.crypto.Sign;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * @author: Alberto Hernández
 * @date: 8/7/20
 */
public class MessageHelper
{

	public static byte[] encode(RlpSerialized rlpSerialized, Sign.SignatureData signatureData) {
		RlpList rlpList = rlpSerialized.serialize(signatureData);
		return RlpEncoder.encode(rlpList);
	}

	public static byte[] encode(RlpSerialized rlpSerialized, long chainId) {
		Sign.SignatureData signatureData = new Sign.SignatureData(longToBytes(chainId), new byte[0], new byte[0]);
		return encode(rlpSerialized, signatureData);
	}

	public static byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(x);
		return buffer.array();
	}

}
