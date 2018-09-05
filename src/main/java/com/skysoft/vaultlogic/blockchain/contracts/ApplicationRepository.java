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
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class ApplicationRepository extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b50604051602080620026cc833981018060405281019080805190602001909291905050508060008173ffffffffffffffffffffffffffffffffffffffff1614151515620000c6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e59c5296200015c6200026c640100000000026401000000009004565b306040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015620001fc578082015181840152602081019050620001df565b50505050905090810190601f1680156200022a5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156200024b57600080fd5b505af115801562000260573d6000803e3d6000fd5b505050505050620002a9565b60606040805190810160405280601681526020017f6170706c69636174696f6e2d7265706f7369746f727900000000000000000000815250905090565b61241380620002b96000396000f3006080604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806301f629c0146100a957806307d8ef9a146100f657806308114c9d1461013b5780639507d39a146101a85780639fdc3a4d14610327578063d32cca47146103cd578063edcce1b31461040e578063f004a7e4146104b5578063f6efa674146104ec578063ff78b45214610592575b600080fd5b3480156100b557600080fd5b506100f460048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506105ff565b005b34801561010257600080fd5b50610139600480360381019080803590602001909291908035906020019082018035906020019190919293919293905050506106d6565b005b34801561014757600080fd5b50610166600480360381019080803590602001909291905050506107ce565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101b457600080fd5b506101d36004803603810190808035906020019092919050505061083d565b60405180806020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838103835288818151815260200191508051906020019080838360005b83811015610281578082015181840152602081019050610266565b50505050905090810190601f1680156102ae5780820380516001836020036101000a031916815260200191505b50838103825286818151815260200191508051906020019080838360005b838110156102e75780820151818401526020810190506102cc565b50505050905090810190601f1680156103145780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b34801561033357600080fd5b50610352600480360381019080803590602001909291905050506108bf565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610392578082015181840152602081019050610377565b50505050905090810190601f1680156103bf5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156103d957600080fd5b506103f86004803603810190808035906020019092919050505061092e565b6040518082815260200191505060405180910390f35b34801561041a57600080fd5b506104b360048036038101908080359060200190929190803590602001908201803590602001919091929391929390803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001919091929391929390803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061099d565b005b3480156104c157600080fd5b506104ea6004803603810190808035906020019092919080359060200190929190505050610b66565b005b3480156104f857600080fd5b5061051760048036038101908080359060200190929190505050610c11565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561055757808201518184015260208101905061053c565b50505050905090810190601f1680156105845780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561059e57600080fd5b506105bd60048036038101908080359060200190929190505050610c80565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61066782826106426040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff16610eb79092919063ffffffff16565b7fecc5a039ee5311e887972ef4263d65344c7ca962ba3ecd33e66faef4ee5333518282604051808381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a15050565b6107708383838080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505061074b6040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff16610fbd9092919063ffffffff16565b7f3908ab5caf44fc3e7a4b58da20fc1794f0301ec457d66b301c4939be57ec650183838360405180848152602001806020018281038252848482818152602001925080828437820191505094505050505060405180910390a1505050565b6000610836826108126040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff166110fc90919063ffffffff16565b9050919050565b6060600060606000806108ac866108886040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff166111f690919063ffffffff16565b9450945094509450945091939590929450565b6060610927826109036040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff1661176f90919063ffffffff16565b9050919050565b6000610996826109726040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff166118bf90919063ffffffff16565b9050919050565b610a718888888080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050508787878080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050508686610a486040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff166119b9909695949392919063ffffffff16565b7fb2aef06aed6c32445d7d5744b2ba436bbe89a3a8c4d06a5f19ebc62b8d3f6b1a888888888888888860405180898152602001806020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184815260200183810383528a8a828181526020019250808284378201915050838103825287878281815260200192508082843782019150509a505050505050505050505060405180910390a15050505050505050565b610bce8282610ba96040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff16611fe29092919063ffffffff16565b7f565fdede77c2d98cf7ef898d489425bd022f8a537b7f7b8e28b0045866347fdf8282604051808381526020018281526020019250505060405180910390a15050565b6060610c7982610c556040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff166120bc90919063ffffffff16565b9050919050565b6000610ce882610cc46040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610cef565b73ffffffffffffffffffffffffffffffffffffffff1661220c90919063ffffffff16565b9050919050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610d9a578082015181840152602081019050610d7f565b50505050905090810190601f168015610dc75780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610de657600080fd5b505af1158015610dfa573d6000803e3d6000fd5b505050506040513d6020811015610e1057600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1614151515610eb2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b919050565b8273ffffffffffffffffffffffffffffffffffffffff16635a2bf25a610f126040805190810160405280601381526020017f6170706c69636174696f6e5f616464726573730000000000000000000000000081525085612306565b836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015610fa057600080fd5b505af1158015610fb4573d6000803e3d6000fd5b50505050505050565b8273ffffffffffffffffffffffffffffffffffffffff1663f58660666110186040805190810160405280600f81526020017f6170706c69636174696f6e5f75726c000000000000000000000000000000000081525085612306565b836040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200180602001828103825283818151815260200191508051906020019080838360005b83811015611092578082015181840152602081019050611077565b50505050905090810190601f1680156110bf5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156110df57600080fd5b505af11580156110f3573d6000803e3d6000fd5b50505050505050565b60008273ffffffffffffffffffffffffffffffffffffffff16634c77e5ba6111596040805190810160405280601181526020017f6170706c69636174696f6e5f6f776e657200000000000000000000000000000081525085612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156111b357600080fd5b505af11580156111c7573d6000803e3d6000fd5b505050506040513d60208110156111dd57600080fd5b8101908080519060200190929190505050905092915050565b6060600060606000808673ffffffffffffffffffffffffffffffffffffffff1663a209a29c61125a6040805190810160405280601081526020017f6170706c69636174696f6e5f6e616d650000000000000000000000000000000081525089612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050600060405180830381600087803b1580156112b457600080fd5b505af11580156112c8573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525060208110156112f257600080fd5b81019080805164010000000081111561130a57600080fd5b8281019050602081018481111561132057600080fd5b815185600182028301116401000000008211171561133d57600080fd5b505092919050505094508673ffffffffffffffffffffffffffffffffffffffff16634c77e5ba6113a26040805190810160405280601181526020017f6170706c69636174696f6e5f6f776e657200000000000000000000000000000081525089612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156113fc57600080fd5b505af1158015611410573d6000803e3d6000fd5b505050506040513d602081101561142657600080fd5b810190808051906020019092919050505093508673ffffffffffffffffffffffffffffffffffffffff1663a209a29c6114946040805190810160405280600f81526020017f6170706c69636174696f6e5f75726c000000000000000000000000000000000081525089612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050600060405180830381600087803b1580156114ee57600080fd5b505af1158015611502573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561152c57600080fd5b81019080805164010000000081111561154457600080fd5b8281019050602081018481111561155a57600080fd5b815185600182028301116401000000008211171561157757600080fd5b505092919050505092508673ffffffffffffffffffffffffffffffffffffffff16634c77e5ba6115dc6040805190810160405280601381526020017f6170706c69636174696f6e5f616464726573730000000000000000000000000081525089612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561163657600080fd5b505af115801561164a573d6000803e3d6000fd5b505050506040513d602081101561166057600080fd5b810190808051906020019092919050505091508673ffffffffffffffffffffffffffffffffffffffff1663e82617fb6116ce6040805190810160405280601281526020017f6170706c69636174696f6e20737461747573000000000000000000000000000081525089612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561172857600080fd5b505af115801561173c573d6000803e3d6000fd5b505050506040513d602081101561175257600080fd5b810190808051906020019092919050505090509295509295909350565b60608273ffffffffffffffffffffffffffffffffffffffff1663a209a29c6117cc6040805190810160405280600f81526020017f6170706c69636174696f6e5f75726c000000000000000000000000000000000081525085612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050600060405180830381600087803b15801561182657600080fd5b505af115801561183a573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561186457600080fd5b81019080805164010000000081111561187c57600080fd5b8281019050602081018481111561189257600080fd5b81518560018202830111640100000000821117156118af57600080fd5b5050929190505050905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb61191c6040805190810160405280601281526020017f6170706c69636174696f6e20737461747573000000000000000000000000000081525085612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561197657600080fd5b505af115801561198a573d6000803e3d6000fd5b505050506040513d60208110156119a057600080fd5b8101908080519060200190929190505050905092915050565b8673ffffffffffffffffffffffffffffffffffffffff1663ffb4f623611a146040805190810160405280600e81526020017f6170706c69636174696f6e5f696400000000000000000000000000000000000081525089612306565b886040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b158015611a7657600080fd5b505af1158015611a8a573d6000803e3d6000fd5b505050508673ffffffffffffffffffffffffffffffffffffffff1663f5866066611ae96040805190810160405280601081526020017f6170706c69636174696f6e5f6e616d650000000000000000000000000000000081525089612306565b876040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200180602001828103825283818151815260200191508051906020019080838360005b83811015611b63578082015181840152602081019050611b48565b50505050905090810190601f168015611b905780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015611bb057600080fd5b505af1158015611bc4573d6000803e3d6000fd5b505050508673ffffffffffffffffffffffffffffffffffffffff16635a2bf25a611c236040805190810160405280601181526020017f6170706c69636174696f6e5f6f776e657200000000000000000000000000000081525089612306565b866040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015611cb157600080fd5b505af1158015611cc5573d6000803e3d6000fd5b505050508673ffffffffffffffffffffffffffffffffffffffff1663f5866066611d246040805190810160405280600f81526020017f6170706c69636174696f6e5f75726c000000000000000000000000000000000081525089612306565b856040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200180602001828103825283818151815260200191508051906020019080838360005b83811015611d9e578082015181840152602081019050611d83565b50505050905090810190601f168015611dcb5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015611deb57600080fd5b505af1158015611dff573d6000803e3d6000fd5b505050508673ffffffffffffffffffffffffffffffffffffffff16635a2bf25a611e5e6040805190810160405280601381526020017f6170706c69636174696f6e5f616464726573730000000000000000000000000081525089612306565b846040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015611eec57600080fd5b505af1158015611f00573d6000803e3d6000fd5b505050508673ffffffffffffffffffffffffffffffffffffffff1663ffb4f623611f5f6040805190810160405280601281526020017f6170706c69636174696f6e20737461747573000000000000000000000000000081525089612306565b836040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b158015611fc157600080fd5b505af1158015611fd5573d6000803e3d6000fd5b5050505050505050505050565b8273ffffffffffffffffffffffffffffffffffffffff1663ffb4f62361203d6040805190810160405280601281526020017f6170706c69636174696f6e20737461747573000000000000000000000000000081525085612306565b836040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b15801561209f57600080fd5b505af11580156120b3573d6000803e3d6000fd5b50505050505050565b60608273ffffffffffffffffffffffffffffffffffffffff1663a209a29c6121196040805190810160405280601081526020017f6170706c69636174696f6e5f6e616d650000000000000000000000000000000081525085612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050600060405180830381600087803b15801561217357600080fd5b505af1158015612187573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525060208110156121b157600080fd5b8101908080516401000000008111156121c957600080fd5b828101905060208101848111156121df57600080fd5b81518560018202830111640100000000821117156121fc57600080fd5b5050929190505050905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff16634c77e5ba6122696040805190810160405280601381526020017f6170706c69636174696f6e5f616464726573730000000000000000000000000081525085612306565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156122c357600080fd5b505af11580156122d7573d6000803e3d6000fd5b505050506040513d60208110156122ed57600080fd5b8101908080519060200190929190505050905092915050565b600082826040516020018083805190602001908083835b602083101515612342578051825260208201915060208101905060208303925061231d565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156123b2578051825260208201915060208101905060208303925061238d565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390209050929150505600a165627a7a72305820dd8104c0786428b144dad80ab12ee67c716797583b08b57be7327c7c5e55c5db0029";

    public static final String FUNC_SAVE = "save";

    public static final String FUNC_GET = "get";

    public static final String FUNC_GETAPPLICATIONNAME = "getApplicationName";

    public static final String FUNC_GETAPPLICATIONOWNER = "getApplicationOwner";

    public static final String FUNC_GETAPPLICATIONURL = "getApplicationUrl";

    public static final String FUNC_SETAPPLICATIONURL = "setApplicationUrl";

    public static final String FUNC_GETAPPLICATIONADDRESS = "getApplicationAddress";

    public static final String FUNC_SETAPPLICATIONADDRESS = "setApplicationAddress";

    public static final String FUNC_GETAPPLICATIONSTATUS = "getApplicationStatus";

    public static final String FUNC_SETAPPLICATIONSTATUS = "setApplicationStatus";

    public static final Event APPLICATIONSAVED_EVENT = new Event("ApplicationSaved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event APPLICATIONURLUPDATED_EVENT = new Event("ApplicationUrlUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event APPLICATIONADDRESSUPDATED_EVENT = new Event("ApplicationAddressUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event APPLICATIONSTATUSUPDATED_EVENT = new Event("ApplicationStatusUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x78cb4288be8e069e8acdb6697d68fba06229ac57");
    }

    protected ApplicationRepository(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ApplicationRepository(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<ApplicationRepository> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String registryAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(registryAddr)));
        return deployRemoteCall(ApplicationRepository.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<ApplicationRepository> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String registryAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(registryAddr)));
        return deployRemoteCall(ApplicationRepository.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<ApplicationSavedEventResponse> getApplicationSavedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPLICATIONSAVED_EVENT, transactionReceipt);
        ArrayList<ApplicationSavedEventResponse> responses = new ArrayList<ApplicationSavedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApplicationSavedEventResponse typedResponse = new ApplicationSavedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.appAddr = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApplicationSavedEventResponse> applicationSavedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApplicationSavedEventResponse>() {
            @Override
            public ApplicationSavedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPLICATIONSAVED_EVENT, log);
                ApplicationSavedEventResponse typedResponse = new ApplicationSavedEventResponse();
                typedResponse.log = log;
                typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.owner = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.appAddr = (String) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ApplicationSavedEventResponse> applicationSavedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPLICATIONSAVED_EVENT));
        return applicationSavedEventObservable(filter);
    }

    public List<ApplicationUrlUpdatedEventResponse> getApplicationUrlUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPLICATIONURLUPDATED_EVENT, transactionReceipt);
        ArrayList<ApplicationUrlUpdatedEventResponse> responses = new ArrayList<ApplicationUrlUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApplicationUrlUpdatedEventResponse typedResponse = new ApplicationUrlUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApplicationUrlUpdatedEventResponse> applicationUrlUpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApplicationUrlUpdatedEventResponse>() {
            @Override
            public ApplicationUrlUpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPLICATIONURLUPDATED_EVENT, log);
                ApplicationUrlUpdatedEventResponse typedResponse = new ApplicationUrlUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ApplicationUrlUpdatedEventResponse> applicationUrlUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPLICATIONURLUPDATED_EVENT));
        return applicationUrlUpdatedEventObservable(filter);
    }

    public List<ApplicationAddressUpdatedEventResponse> getApplicationAddressUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPLICATIONADDRESSUPDATED_EVENT, transactionReceipt);
        ArrayList<ApplicationAddressUpdatedEventResponse> responses = new ArrayList<ApplicationAddressUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApplicationAddressUpdatedEventResponse typedResponse = new ApplicationAddressUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.appAddr = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApplicationAddressUpdatedEventResponse> applicationAddressUpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApplicationAddressUpdatedEventResponse>() {
            @Override
            public ApplicationAddressUpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPLICATIONADDRESSUPDATED_EVENT, log);
                ApplicationAddressUpdatedEventResponse typedResponse = new ApplicationAddressUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.appAddr = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ApplicationAddressUpdatedEventResponse> applicationAddressUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPLICATIONADDRESSUPDATED_EVENT));
        return applicationAddressUpdatedEventObservable(filter);
    }

    public List<ApplicationStatusUpdatedEventResponse> getApplicationStatusUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPLICATIONSTATUSUPDATED_EVENT, transactionReceipt);
        ArrayList<ApplicationStatusUpdatedEventResponse> responses = new ArrayList<ApplicationStatusUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApplicationStatusUpdatedEventResponse typedResponse = new ApplicationStatusUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApplicationStatusUpdatedEventResponse> applicationStatusUpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApplicationStatusUpdatedEventResponse>() {
            @Override
            public ApplicationStatusUpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPLICATIONSTATUSUPDATED_EVENT, log);
                ApplicationStatusUpdatedEventResponse typedResponse = new ApplicationStatusUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ApplicationStatusUpdatedEventResponse> applicationStatusUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPLICATIONSTATUSUPDATED_EVENT));
        return applicationStatusUpdatedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> save(BigInteger appId, String name, String owner, String url, String appAddr, BigInteger status) {
        final Function function = new Function(
                FUNC_SAVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Address(owner), 
                new org.web3j.abi.datatypes.Utf8String(url), 
                new org.web3j.abi.datatypes.Address(appAddr), 
                new org.web3j.abi.datatypes.generated.Uint256(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple5<String, String, String, String, BigInteger>> get(BigInteger appId) {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<String, String, String, String, BigInteger>>(
                new Callable<Tuple5<String, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple5<String, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<String> getApplicationName(BigInteger appId) {
        final Function function = new Function(FUNC_GETAPPLICATIONNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getApplicationOwner(BigInteger appId) {
        final Function function = new Function(FUNC_GETAPPLICATIONOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getApplicationUrl(BigInteger appId) {
        final Function function = new Function(FUNC_GETAPPLICATIONURL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setApplicationUrl(BigInteger appId, String url) {
        final Function function = new Function(
                FUNC_SETAPPLICATIONURL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId), 
                new org.web3j.abi.datatypes.Utf8String(url)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getApplicationAddress(BigInteger appId) {
        final Function function = new Function(FUNC_GETAPPLICATIONADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setApplicationAddress(BigInteger appId, String appAddr) {
        final Function function = new Function(
                FUNC_SETAPPLICATIONADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId), 
                new org.web3j.abi.datatypes.Address(appAddr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getApplicationStatus(BigInteger appId) {
        final Function function = new Function(FUNC_GETAPPLICATIONSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setApplicationStatus(BigInteger appId, BigInteger status) {
        final Function function = new Function(
                FUNC_SETAPPLICATIONSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId), 
                new org.web3j.abi.datatypes.generated.Uint256(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static ApplicationRepository load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ApplicationRepository(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ApplicationRepository load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ApplicationRepository(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class ApplicationSavedEventResponse {
        public Log log;

        public BigInteger appId;

        public String name;

        public String owner;

        public String url;

        public String appAddr;

        public BigInteger status;

        @Override
        public String toString() {
            return "ApplicationSavedEventResponse{" +
                    ", appId=" + appId +
                    ", name='" + name + '\'' +
                    ", owner='" + owner + '\'' +
                    ", url='" + url + '\'' +
                    ", appAddr='" + appAddr + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    public static class ApplicationUrlUpdatedEventResponse {
        public Log log;

        public BigInteger appId;

        public String url;
    }

    public static class ApplicationAddressUpdatedEventResponse {
        public Log log;

        public BigInteger appId;

        public String appAddr;
    }

    public static class ApplicationStatusUpdatedEventResponse {
        public Log log;

        public BigInteger appId;

        public BigInteger status;
    }

}
