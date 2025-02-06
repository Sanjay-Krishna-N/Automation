from datetime import datetime
import requests
import pandas as pd
import os
import json

input_file = r'D:\UOB - Finastra\Automation\Api Testing\requests.txt'
output_file = r'D:\UOB - Finastra\Automation\Api Testing\api_responses.xlsx'

if not os.path.exists(input_file):
    print(f"Input file not found: {input_file}")
else:
    with open(input_file, 'r') as file:
        lines = file.readlines()
    results = []
    for line in lines:
        url, request_body = line.strip().split('|')
        try:
            response = requests.post(url, json=json.loads(request_body))
            response_text = response.text
            response_code = response.status_code
            response_headers = dict(response.headers)
        except Exception as e:
            response_text = str(e)
            response_code = None
            response_headers = None
        results.append({
            'URL': url,
            'Request': request_body,
            'Response Code': response_code,
            'Response Headers': response_headers,
            'Response Body': response_text
        })
    df = pd.DataFrame(results)

    df.to_excel(output_file, index=False)

    print(f"API responses written to {output_file}")
