import java.util.LinkedList;

public class Chain {

    public LinkedList<Block> chain;
    public int difficulty = 1;
    public Chain(){
        this.chain = new LinkedList<>();
        Block firstBlock = new Block("创币交易","");
        this.chain.push(firstBlock);
        System.out.println("创币交易成功");
    }


    //下面两个函数使得挖矿难度difficulty随着链的长度递增
    public int getPresentSize(){
        return this.chain.size();
    }
    public void setPresentDifficulty(){
        this.difficulty = getPresentSize();
    }
    //往链中添加一个新的block
     public void addBlockToChain(Block newblock){
        //data// 前一个block的hash
         newblock.previoushash = getLatestBlock().hash;
         //之前计算没有引入随机数，不需要满足任何条件就可以加入区块链
         //newblock.hash = newblock.calculateHash();
         //只有当newblock的hash值满足mine（）的条件后才可以加入到区块链中
         newblock.hash = this.mine(newblock);
         //这个hash需要满足一定的条件，才能将此block加入到区块中；比如前几位为0，借此引入PoW机制
         this.chain.push(newblock);
         //System.out.println("添加新区块完成");
     }

     //符合难度要求的答案，设置前difficulty位为0
     public String getAnswer(){
        setPresentDifficulty();
        String answer = "";
        for(int i=0;i<difficulty;i++){
            answer+="0";
        }
        return answer;
     }

     //挖矿--计算符合区块链难度要求即answer要求的hash
     public String mine(Block newblock){
        //交易记录是已知的，挖矿是为了争夺记账权
        //Block newBlock = new Block("转账肆拾元","");
        while(true){
            newblock.hash = newblock.calculateHash();
            //System.out.println(newblock.hash.substring(0,difficulty));
            //工作量证明机制 字符串比较不要使用"=="，而是使用equals()
            if(newblock.hash.substring(0,difficulty).equals(this.getAnswer())){
                break;
            }else {
                newblock.nonce++;
            }
        }
        System.out.println("挖矿结束--"+newblock.hash);
        return newblock.hash;
     }

     //获取当前最后一个节点
     public Block getLatestBlock(){
        return getBlockofi(chain.size()-1);
     }

     //获取第i个block
    public Block getBlockofi(int i){
        if(i < this.chain.size()){
            Object[] objects = chain.toArray();
            return (Block)objects[chain.size()-i-1];
        }
        return null;
    }

     //输出区块链信息
    public void printChain(){

        for(int i =0;i<chain.size();i++){
            System.out.println(getBlockofi(i).getData()+"  "+getBlockofi(i).previoushash+"  "+getBlockofi(i).calculateHash());
        }
    }

    //验证当前的区块链是否合法
    //1,验证当前区块的previoushash是否是其前一个区块的hash
    //2，验证当前的hash值是否是当的block的data，previoushash和nonce运算生成的
    public Boolean validateChain(){
//        for(int j = 1;j<this.chain.size();j++){
//            System.out.println("当前区块的计算hash值为： "+this.getBlockofi(j).calculateHash()+"  前一个区块的hash值为： "+this.getBlockofi(j-1).hash);
//        }
        if(this.chain.size()==1){
            if(this.getBlockofi(0).hash == this.getBlockofi(0).calculateHash()){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            for(int i=1;i<this.chain.size();i++){
                if(!this.getBlockofi(i).hash.equals(this.getBlockofi(i).calculateHash())){
                    System.out.println("数据被篡改");
                    return false;
                }
                else if(!this.getBlockofi(i-1).calculateHash().equals(this.getBlockofi(i).previoushash)){
                    System.out.println("前后区块链接断裂");
                    return false;
                }
            }
        }
        return true;
    }
}
