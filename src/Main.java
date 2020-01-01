public class Main {


    public static void main(String[] args) {
        Block block1 = new Block("转账十一元","");
        Block block2 = new Block("转账二十元","");
        Block block3 = new Block("转账三十元","");
        Block block4 = new Block("转账四十元","");
        Block block5 = new Block("转账五十元","");

        Chain chain =  new Chain();

        chain.addBlockToChain(block1);
        chain.addBlockToChain(block2);
        chain.addBlockToChain(block3);
        chain.addBlockToChain(block4);
        chain.addBlockToChain(block5);
       // chain.addBlockToChain(block6);
//        chain.addBlockToChain(block7);
        System.out.println("================================以下是区块链信息==================================");
        chain.printChain();
        Boolean isValidate = chain.validateChain();
        System.out.println(isValidate);
        //尝试篡改数据
        block3.setData("转账一百元");
        System.out.println("============================以下篡改后的区块链信息=================================");
        chain.printChain();
        System.out.println(chain.validateChain());
    }
}
