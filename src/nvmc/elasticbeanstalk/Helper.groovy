package nvmc.elasticbeanstalk

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalk
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClientBuilder
import com.amazonaws.services.elasticbeanstalk.model.*
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.PutObjectRequest
import groovy.util.logging.Slf4j

@Slf4j
class Helper {
    String accessKeyId
    String secretAccessKey
    AWSCredentialsProvider awsCredentials
    Region awsRegion
    AWSElasticBeanstalk awsBeanstalkClient
    AmazonS3Client s3Client

    final static Long DELAY_INTERVAL = 1000 * 30
    final static Integer MAX_TRIES = 100

    Helper(accessKeyId, secretAccessKey, region) {
        this.accessKeyId = accessKeyId
        this.secretAccessKey = secretAccessKey
        awsCredentials = new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey))
        awsBeanstalkClient = AWSElasticBeanstalkClientBuilder.standard()
                .withRegion(region)
                .withCredentials(awsCredentials)
                .build()
        log.debug "Created new AWS Elastic Beanstalk Client..."
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(awsCredentials)
                .build()
        log.debug "Created new AWS S3 Client..."
    }
}
