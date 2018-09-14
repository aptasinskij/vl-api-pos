package com.skysoft.vaultlogic.blockchain.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.contracts.SmartContractEvent;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class CashInStorage extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b5060405160208062002a6e833981018060405281019080805190602001909291905050508060008173ffffffffffffffffffffffffffffffffffffffff1614151515620000c6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e59c5296200015c6200026c640100000000026401000000009004565b306040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015620001fc578082015181840152602081019050620001df565b50505050905090810190601f1680156200022a5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156200024b57600080fd5b505af115801562000260573d6000803e3d6000fd5b505050505050620002a9565b60606040805190810160405280600f81526020017f636173682d696e2d73746f726167650000000000000000000000000000000000815250905090565b6127b580620002b96000396000f3006080604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631e010439146100ca5780631f2f4bfd1461010b57806333263ad61461017857806333d43dbd146101b95780634a4b9feb146102375780635c622a0e1461026e5780636a84f717146102af5780636e19cb181461031a57806382e408131461035b57806390bf10b0146103925780639507d39a146103ef578063a9bdb60114610478578063baa135d4146104cf575b600080fd5b3480156100d657600080fd5b506100f560048036038101908080359060200190929190505050610543565b6040518082815260200191505060405180910390f35b34801561011757600080fd5b50610136600480360381019080803590602001909291905050506105b2565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561018457600080fd5b506101a360048036038101908080359060200190929190505050610621565b6040518082815260200191505060405180910390f35b3480156101c557600080fd5b506101ee6004803603810190808035906020019092919080359060200190929190505050610690565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390f35b34801561024357600080fd5b5061026c6004803603810190808035906020019092919080359060200190929190505050610706565b005b34801561027a57600080fd5b50610299600480360381019080803590602001909291905050506107b1565b6040518082815260200191505060405180910390f35b3480156102bb57600080fd5b5061030460048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610820565b6040518082815260200191505060405180910390f35b34801561032657600080fd5b5061034560048036038101908080359060200190929190505050610910565b6040518082815260200191505060405180910390f35b34801561036757600080fd5b50610390600480360381019080803590602001909291908035906020019092919050505061097f565b005b34801561039e57600080fd5b506103ed60048036038101908080359060200190929190803590602001908201803590602001919091929391929390803590602001908201803590602001919091929391929390505050610a2a565b005b3480156103fb57600080fd5b5061041a60048036038101908080359060200190929190505050610af9565b604051808681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018481526020018381526020018281526020019550505050505060405180910390f35b34801561048457600080fd5b506104cd60048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610b7a565b005b3480156104db57600080fd5b506104fa60048036038101908080359060200190929190505050610c5c565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390f35b60006105ab826105876040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16610efc90919063ffffffff16565b9050919050565b600061061a826105f66040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16610ff690919063ffffffff16565b9050919050565b6000610689826106656040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff166110f090919063ffffffff16565b9050919050565b6000806106fb84846106d66040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff166111ea9092919063ffffffff16565b915091509250929050565b61076e82826107496040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff166113db9092919063ffffffff16565b7f8675c9b21154c77f765df18fbb6c2f5bb9ee8d28c9d5de2f2844919fa56d91648282604051808381526020018281526020019250505060405180910390a15050565b6000610819826107f56040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff166114b590919063ffffffff16565b9050919050565b600061088c8484846108666040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff166115af909392919063ffffffff16565b90507fa50eff91a397e683bdf2380ace809bbdc161da6cdd20f8b88995e4df7c82ffab81858585604051808581526020018481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200194505050505060405180910390a19392505050565b6000610978826109546040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16611b1690919063ffffffff16565b9050919050565b6109e782826109c26040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16611c109092919063ffffffff16565b7f17422b0bc45a88ade572f0c94bf86f21f9a863d8b942193fe5bf3572d715974f8282604051808381526020018281526020019250505060405180910390a15050565b610af285858580806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050848480806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050610acc6040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16611cea909392919063ffffffff16565b5050505050565b6000806000806000610b6786610b436040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16611d4790919063ffffffff16565b9450945094509450945091939590929450565b610be4838383610bbe6040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16612213909392919063ffffffff16565b7f20181f140c72f8b11487b346a4401843072d10f05b4a1d3f23e5d0df110676ef838383604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a1505050565b600080610cc583610ca16040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16610ff690919063ffffffff16565b9150610d2d83610d096040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610d34565b73ffffffffffffffffffffffffffffffffffffffff16611b1690919063ffffffff16565b9050915091565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610ddf578082015181840152602081019050610dc4565b50505050905090810190601f168015610e0c5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610e2b57600080fd5b505af1158015610e3f573d6000803e3d6000fd5b505050506040513d6020811015610e5557600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1614151515610ef7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b919050565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb610f596040805190810160405280600f81526020017f636173685f696e5f62616c616e63650000000000000000000000000000000000815250856125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015610fb357600080fd5b505af1158015610fc7573d6000803e3d6000fd5b505050506040513d6020811015610fdd57600080fd5b8101908080519060200190929190505050905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff16634c77e5ba6110536040805190810160405280601381526020017f636173685f696e5f6170706c69636174696f6e00000000000000000000000000815250856125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156110ad57600080fd5b505af11580156110c1573d6000803e3d6000fd5b505050506040513d60208110156110d757600080fd5b8101908080519060200190929190505050905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb61114d6040805190810160405280601281526020017f636173685f696e5f73706c69745f73697a650000000000000000000000000000815250856125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156111a757600080fd5b505af11580156111bb573d6000803e3d6000fd5b505050506040513d60208110156111d157600080fd5b8101908080519060200190929190505050905092915050565b6000808473ffffffffffffffffffffffffffffffffffffffff16634c77e5ba6112496040805190810160405280601581526020017f636173685f696e5f73706c69745f706172746965730000000000000000000000815250878761269f565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156112a357600080fd5b505af11580156112b7573d6000803e3d6000fd5b505050506040513d60208110156112cd57600080fd5b810190808051906020019092919050505091508473ffffffffffffffffffffffffffffffffffffffff1663e82617fb61133c6040805190810160405280601281526020017f636173685f696e5f73706c69745f666565730000000000000000000000000000815250878761269f565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561139657600080fd5b505af11580156113aa573d6000803e3d6000fd5b505050506040513d60208110156113c057600080fd5b81019080805190602001909291905050509050935093915050565b8273ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236114366040805190810160405280600f81526020017f636173685f696e5f62616c616e63650000000000000000000000000000000000815250856125be565b836040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b15801561149857600080fd5b505af11580156114ac573d6000803e3d6000fd5b50505050505050565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb6115126040805190810160405280600e81526020017f636173685f696e5f737461747573000000000000000000000000000000000000815250856125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561156c57600080fd5b505af1158015611580573d6000803e3d6000fd5b505050506040513d602081101561159657600080fd5b8101908080519060200190929190505050905092915050565b60008473ffffffffffffffffffffffffffffffffffffffff1663e82617fb60405160200180806020018281038252600b8152602001807f43617368496e496e6465780000000000000000000000000000000000000000008152506020019150506040516020818303038152906040526040518082805190602001908083835b602083101515611653578051825260208201915060208101905060208303925061162e565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156116da57600080fd5b505af11580156116ee573d6000803e3d6000fd5b505050506040513d602081101561170457600080fd5b810190808051906020019092919050505090508473ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236117726040805190810160405280601281526020017f636173685f696e5f73657373696f6e5f69640000000000000000000000000000815250846125be565b866040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b1580156117d457600080fd5b505af11580156117e8573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff16635a2bf25a6118476040805190810160405280601381526020017f636173685f696e5f6170706c69636174696f6e00000000000000000000000000815250846125be565b856040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1580156118d557600080fd5b505af11580156118e9573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236119486040805190810160405280600e81526020017f636173685f696e5f737461747573000000000000000000000000000000000000815250846125be565b846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b1580156119aa57600080fd5b505af11580156119be573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663ffb4f62360405160200180806020018281038252600b8152602001807f43617368496e496e6465780000000000000000000000000000000000000000008152506020019150506040516020818303038152906040526040518082805190602001908083835b602083101515611a645780518252602082019150602081019050602083039250611a3f565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600184016040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b158015611af657600080fd5b505af1158015611b0a573d6000803e3d6000fd5b50505050949350505050565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb611b736040805190810160405280601281526020017f636173685f696e5f73657373696f6e5f69640000000000000000000000000000815250856125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015611bcd57600080fd5b505af1158015611be1573d6000803e3d6000fd5b505050506040513d6020811015611bf757600080fd5b8101908080519060200190929190505050905092915050565b8273ffffffffffffffffffffffffffffffffffffffff1663ffb4f623611c6b6040805190810160405280600e81526020017f636173685f696e5f737461747573000000000000000000000000000000000000815250856125be565b836040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b158015611ccd57600080fd5b505af1158015611ce1573d6000803e3d6000fd5b50505050505050565b60008090505b8251811015611d4057611d3385858584815181101515611d0c57fe5b906020019060200201518585815181101515611d2457fe5b90602001906020020151612213565b8080600101915050611cf0565b5050505050565b60008060008060008673ffffffffffffffffffffffffffffffffffffffff1663e82617fb611daa6040805190810160405280601281526020017f636173685f696e5f73657373696f6e5f69640000000000000000000000000000815250896125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015611e0457600080fd5b505af1158015611e18573d6000803e3d6000fd5b505050506040513d6020811015611e2e57600080fd5b810190808051906020019092919050505094508673ffffffffffffffffffffffffffffffffffffffff16634c77e5ba611e9c6040805190810160405280601381526020017f636173685f696e5f6170706c69636174696f6e00000000000000000000000000815250896125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015611ef657600080fd5b505af1158015611f0a573d6000803e3d6000fd5b505050506040513d6020811015611f2057600080fd5b810190808051906020019092919050505093508673ffffffffffffffffffffffffffffffffffffffff1663e82617fb611f8e6040805190810160405280600f81526020017f636173685f696e5f62616c616e63650000000000000000000000000000000000815250896125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015611fe857600080fd5b505af1158015611ffc573d6000803e3d6000fd5b505050506040513d602081101561201257600080fd5b810190808051906020019092919050505092508673ffffffffffffffffffffffffffffffffffffffff1663e82617fb6120806040805190810160405280600e81526020017f636173685f696e5f737461747573000000000000000000000000000000000000815250896125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156120da57600080fd5b505af11580156120ee573d6000803e3d6000fd5b505050506040513d602081101561210457600080fd5b810190808051906020019092919050505091508673ffffffffffffffffffffffffffffffffffffffff1663e82617fb6121726040805190810160405280601281526020017f636173685f696e5f73706c69745f73697a650000000000000000000000000000815250896125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156121cc57600080fd5b505af11580156121e0573d6000803e3d6000fd5b505050506040513d60208110156121f657600080fd5b810190808051906020019092919050505090509295509295909350565b60008473ffffffffffffffffffffffffffffffffffffffff1663e82617fb6122706040805190810160405280601281526020017f636173685f696e5f73706c69745f73697a650000000000000000000000000000815250876125be565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156122ca57600080fd5b505af11580156122de573d6000803e3d6000fd5b505050506040513d60208110156122f457600080fd5b810190808051906020019092919050505090508473ffffffffffffffffffffffffffffffffffffffff16635a2bf25a6123636040805190810160405280601581526020017f636173685f696e5f73706c69745f706172746965730000000000000000000000815250878561269f565b856040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1580156123f157600080fd5b505af1158015612405573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236124656040805190810160405280601281526020017f636173685f696e5f73706c69745f666565730000000000000000000000000000815250878561269f565b846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b1580156124c757600080fd5b505af11580156124db573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663ffb4f62361253a6040805190810160405280601281526020017f636173685f696e5f73706c69745f73697a650000000000000000000000000000815250876125be565b600184016040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b15801561259f57600080fd5b505af11580156125b3573d6000803e3d6000fd5b505050505050505050565b600082826040516020018083805190602001908083835b6020831015156125fa57805182526020820191506020810190506020830392506125d5565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b60208310151561266a5780518252602082019150602081019050602083039250612645565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020905092915050565b60008383836040516020018084805190602001908083835b6020831015156126dc57805182526020820191506020810190506020830392506126b7565b6001836020036101000a03801982511681845116808217855250505050505090500183815260200182815260200193505050506040516020818303038152906040526040518082805190602001908083835b602083101515612753578051825260208201915060208101905060208303925061272e565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020905093925050505600a165627a7a72305820f2fcaff5121b3f7275fac6283e8242134989a3edf0c2e2ec05c45c71f039d5040029";

    public static final String FUNC_SAVE = "save";

    public static final String FUNC_GET = "get";

    public static final String FUNC_GETSESSIONID = "getSessionId";

    public static final String FUNC_GETAPPLICATION = "getApplication";

    public static final String FUNC_GETAPPLICATIONANDSESSIONID = "getApplicationAndSessionId";

    public static final String FUNC_SETBALANCE = "setBalance";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_SETSTATUS = "setStatus";

    public static final String FUNC_GETSTATUS = "getStatus";

    public static final String FUNC_ADDSPLIT = "addSplit";

    public static final String FUNC_ADDSPLITS = "addSplits";

    public static final String FUNC_GETSPLITSIZE = "getSplitSize";

    public static final String FUNC_GETSPLIT = "getSplit";

    public static final Event CASHINSAVED_EVENT = new Event("CashInSaved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHINBALANCEUPDATED_EVENT = new Event("CashInBalanceUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHINSTATUSUPDATED_EVENT = new Event("CashInStatusUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHINSPLITADDED_EVENT = new Event("CashInSplitAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x0c50a9579c0ebb4bf3235cd3b4f5210ff545ba4b");
    }

    protected CashInStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CashInStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<CashInStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String registryAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(registryAddr)));
        return deployRemoteCall(CashInStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CashInStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String registryAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(registryAddr)));
        return deployRemoteCall(CashInStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<CashInSavedEventResponse> getCashInSavedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINSAVED_EVENT, transactionReceipt);
        ArrayList<CashInSavedEventResponse> responses = new ArrayList<CashInSavedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInSavedEventResponse typedResponse = new CashInSavedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.application = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInSavedEventResponse> cashInSavedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInSavedEventResponse>() {
            @Override
            public CashInSavedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINSAVED_EVENT, log);
                CashInSavedEventResponse typedResponse = new CashInSavedEventResponse();
                typedResponse.log = log;
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.application = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInSavedEventResponse> cashInSavedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINSAVED_EVENT));
        return cashInSavedEventObservable(filter);
    }

    public List<CashInBalanceUpdatedEventResponse> getCashInBalanceUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINBALANCEUPDATED_EVENT, transactionReceipt);
        ArrayList<CashInBalanceUpdatedEventResponse> responses = new ArrayList<CashInBalanceUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInBalanceUpdatedEventResponse typedResponse = new CashInBalanceUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInBalanceUpdatedEventResponse> cashInBalanceUpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInBalanceUpdatedEventResponse>() {
            @Override
            public CashInBalanceUpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINBALANCEUPDATED_EVENT, log);
                CashInBalanceUpdatedEventResponse typedResponse = new CashInBalanceUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInBalanceUpdatedEventResponse> cashInBalanceUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINBALANCEUPDATED_EVENT));
        return cashInBalanceUpdatedEventObservable(filter);
    }

    public List<CashInStatusUpdatedEventResponse> getCashInStatusUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINSTATUSUPDATED_EVENT, transactionReceipt);
        ArrayList<CashInStatusUpdatedEventResponse> responses = new ArrayList<CashInStatusUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInStatusUpdatedEventResponse typedResponse = new CashInStatusUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInStatusUpdatedEventResponse> cashInStatusUpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInStatusUpdatedEventResponse>() {
            @Override
            public CashInStatusUpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINSTATUSUPDATED_EVENT, log);
                CashInStatusUpdatedEventResponse typedResponse = new CashInStatusUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInStatusUpdatedEventResponse> cashInStatusUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINSTATUSUPDATED_EVENT));
        return cashInStatusUpdatedEventObservable(filter);
    }

    public List<CashInSplitAddedEventResponse> getCashInSplitAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINSPLITADDED_EVENT, transactionReceipt);
        ArrayList<CashInSplitAddedEventResponse> responses = new ArrayList<CashInSplitAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInSplitAddedEventResponse typedResponse = new CashInSplitAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.party = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInSplitAddedEventResponse> cashInSplitAddedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInSplitAddedEventResponse>() {
            @Override
            public CashInSplitAddedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINSPLITADDED_EVENT, log);
                CashInSplitAddedEventResponse typedResponse = new CashInSplitAddedEventResponse();
                typedResponse.log = log;
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.party = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInSplitAddedEventResponse> cashInSplitAddedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINSPLITADDED_EVENT));
        return cashInSplitAddedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> save(BigInteger sessionId, String application, BigInteger status) {
        final Function function = new Function(
                FUNC_SAVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId), 
                new org.web3j.abi.datatypes.Address(application), 
                new org.web3j.abi.datatypes.generated.Uint256(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple5<BigInteger, String, BigInteger, BigInteger, BigInteger>> get(BigInteger channelId) {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<BigInteger, String, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple5<BigInteger, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<BigInteger, String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, String, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getSessionId(BigInteger channelId) {
        final Function function = new Function(FUNC_GETSESSIONID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getApplication(BigInteger channelId) {
        final Function function = new Function(FUNC_GETAPPLICATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<String, BigInteger>> getApplicationAndSessionId(BigInteger channelId) {
        final Function function = new Function(FUNC_GETAPPLICATIONANDSESSIONID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setBalance(BigInteger channelId, BigInteger amount) {
        final Function function = new Function(
                FUNC_SETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getBalance(BigInteger channelId) {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setStatus(BigInteger channelId, BigInteger status) {
        final Function function = new Function(
                FUNC_SETSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.generated.Uint256(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getStatus(BigInteger channelId) {
        final Function function = new Function(FUNC_GETSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addSplit(BigInteger channelId, String party, BigInteger amount) {
        final Function function = new Function(
                FUNC_ADDSPLIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.Address(party), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addSplits(BigInteger channelId, List<String> parties, List<BigInteger> amounts) {
        final Function function = new Function(
                FUNC_ADDSPLITS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(parties, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(amounts, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getSplitSize(BigInteger channelId) {
        final Function function = new Function(FUNC_GETSPLITSIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<String, BigInteger>> getSplit(BigInteger channelId, BigInteger subIndex) {
        final Function function = new Function(FUNC_GETSPLIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.generated.Uint256(subIndex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public static CashInStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CashInStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CashInStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CashInStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface CashInStorageEvent extends SmartContractEvent {
    }

    public static class CashInSavedEventResponse implements CashInStorageEvent {
        public Log log;

        public BigInteger channelId;

        public BigInteger sessionId;

        public String application;

        public BigInteger status;
    }

    public static class CashInBalanceUpdatedEventResponse implements CashInStorageEvent {
        public Log log;

        public BigInteger channelId;

        public BigInteger amount;
    }

    public static class CashInStatusUpdatedEventResponse implements CashInStorageEvent {
        public Log log;

        public BigInteger channelId;

        public BigInteger status;
    }

    public static class CashInSplitAddedEventResponse implements CashInStorageEvent {
        public Log log;

        public BigInteger channelId;

        public String party;

        public BigInteger amount;
    }
}
