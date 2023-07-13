/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//package org.apache.hadoop.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;


public class GenreRecommend {
	/**
	 * Mapper
	 * @author zijin
	 *
	 */
	public static class GenreRecommendMapper extends Mapper<Object, Text, Text, Text>{	
		
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
			String line = value.toString();
			String id = line.substring(0, line.indexOf("\t"));
			String genreStr = line.substring(line.indexOf("\t")).trim();
			StringTokenizer tokenizer = new StringTokenizer(genreStr);
			while (tokenizer.hasMoreTokens()){
				String genre = tokenizer.nextToken();
				
				Text word = new Text(genre);
				context.write(word, new Text(id));
			}
		}
	}
	
	/**
	 * Reducer
	 * @author zijin
	 *
	 */
	public static class GenreRecommendReducer extends Reducer<Text, Text, Text, Text>{
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
			StringBuilder sb = new StringBuilder();
			for (Text val : values){
				sb.append(val.toString() + " ");
			}
			context.write(key, new Text(sb.toString()));
		}
	}
	
	public static void main(String[] args) throws Exception{
//		init();
		
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length < 2){
			System.err.println("Usage: wordcount <in> [<in>...] <out>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(GenreRecommend.class);
		job.setJobName("Calculate the average rating for each genre");
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		job.setMapperClass(GenreRecommendMapper.class);
		job.setCombinerClass(GenreRecommendReducer.class);
		job.setReducerClass(GenreRecommendReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
//		FileOutputFormat.setOutputPath(job, new Path(otherArgs[otherArgs.length - 1]));
		System.exit(job.waitForCompletion(true)? 0 : 1);
		
	}
}