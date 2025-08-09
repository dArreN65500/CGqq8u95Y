// 代码生成时间: 2025-08-09 13:21:50
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughFieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;

@SpringBootApplication
@EnableBatchProcessing
public class CsvBatchProcessor {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private ResourceLoader resourceLoader;

    // Define the job
    @Bean
    public Job csvFileProcessingJob() throws Exception {
        return jobBuilderFactory.get("csvFileProcessingJob")
                .incrementer()
                .start(csvFileStep())
                .next(endStep())
                .build();
    }

    // Define the step for CSV file processing
    @Bean
    public Step csvFileStep() throws IOException {
        return stepBuilderFactory.get("csvFileStep")
                .<CsvField, CsvField>chunk(10)
                .reader(csvFileReader())
                .writer(csvFileWriter())
                .build();
    }

    // Define the step for job completion
    @Bean
    public Step endStep() {
        return stepBuilderFactory.get("endStep")
                .tasklet((contribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                }).build();
    }

    // Define the CSV file reader
    @Bean
    public FlatFileItemReader<CsvField> csvFileReader() {
        FlatFileItemReader<CsvField> reader = new FlatFileItemReader<>();
        reader.setResource(resourceLoader.getResource("classpath:input.csv"));
        reader.setLineMapper(new DefaultLineMapper<CsvField>() {
            protected void initLineMapper() {
                setLineTokenizer(new DelimitedLineTokenizer(","));
                setFieldSetMapper(new BeanWrapperFieldSetMapper<>(CsvField.class));
            }
        });
        return reader;
    }

    // Define the CSV file writer
    @Bean
    public FlatFileItemWriter<CsvField> csvFileWriter() {
        FlatFileItemWriter<CsvField> writer = new FlatFileItemWriter<>();
        writer.setResource(resourceLoader.getResource("classpath:output.csv"));
        writer.setLineSeparator("
");
        writer.setFieldExtractor(new PassThroughFieldExtractor<>());
        writer.setLineTokenizer(new DelimitedLineTokenizer(",", false, false));
        return writer;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CsvBatchProcessor.class, args);
        CsvBatchProcessor processor = new CsvBatchProcessor();
        JobExecution jobExecution = processor.jobLauncher.run(processor.csvFileProcessingJob(), new JobParametersBuilder().toJobParameters());

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("CSV processing completed successfully");
        } else {
            System.out.println("CSV processing failed");
        }
    }
}

// Define the CSV field class
class CsvField {
    private String field1;
    private String field2;
    // getters and setters
}
