package com.github.ah.blockchain.msg.signer.test;

import com.github.ah.blockchain.msg.signer.rlp.RlpSerialized;
import org.web3j.crypto.Sign;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Bytes;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: Alberto Hernández
 * @date: 9/7/20
 */
public class TestFactory
{

	public static RlpSerialized buildMessage () {
		RlpSerialized message = new RlpSerialized()
		{
			@Override public RlpList serialize(Sign.SignatureData signatureData)
			{
				List<RlpType> result = new ArrayList();

				result.add(RlpString.create(BigInteger.valueOf(9)));
				result.add(RlpString.create(BigInteger.valueOf(20000000000L)));
				result.add(RlpString.create(BigInteger.valueOf(21000)));

				// an empty to address (contract creation) should not be encoded as a numeric 0 value
				result.add(RlpString.create(Numeric.hexStringToByteArray("0x3535353535353535353535353535353535353535")));

				result.add(RlpString.create(BigInteger.valueOf(1000000000000000000L)));

				// value field will already be hex encoded, so we need to convert into binary first
				byte[] data = Numeric.hexStringToByteArray("");
				result.add(RlpString.create(data));

				if (signatureData != null) {
					result.add(RlpString.create(Bytes.trimLeadingZeroes(signatureData.getV())));
					result.add(RlpString.create(Bytes.trimLeadingZeroes(signatureData.getR())));
					result.add(RlpString.create(Bytes.trimLeadingZeroes(signatureData.getS())));
				}

				return new RlpList(result);
			}
		};

		return message;

	}
}
