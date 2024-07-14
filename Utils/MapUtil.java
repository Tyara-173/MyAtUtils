Map<Long,Integer> typeMap(long[] arr){
    Map<Long,Integer> map = new HashMap<>();
    for (long value : arr) {
        add(map,value);
    }
    return map;
}

void add(Map<Long,Integer> map,long value){
    if(map.containsKey(value)){
        map.put(value,map.get(value) + 1);
    }else{
        map.put(value,1);
    }
}