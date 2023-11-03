# SPRING MICROSERVICES


## ELK stack

In windows download from the [page](https://www.elastic.co/) official files and then run:

```bash
  #START
  cp logstash.conf LOGSTASH_FOLDER
  cd LOGSTASH_FOLDER
  bin\logstash -f logstash.conf

  #START
  cd ELASTIC_FOLDER
  bin\elasticsearch.bat 
  
  #START
  cd KIBANA_FOLDER
  bin\kibana.bat
  
  #RENEW ELASTIC TOKEN FOR KIBANA
  bin/elasticsearch-create-enrollment-token -s kibana
```