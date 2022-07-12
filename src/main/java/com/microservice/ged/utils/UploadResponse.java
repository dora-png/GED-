package com.microservice.ged.utils;

import java.io.Serializable;
import java.util.Date;

public class UploadResponse implements Serializable{
			private static final long serialVersionUID = 1L;
			private String uploadLocation;
			private Date uploadDate;
			
			public UploadResponse () {
				
			}
			
			public UploadResponse(String uploadLocation) {
				super();
				this.uploadLocation = uploadLocation;
				this.uploadDate = new Date();
			}

			public String getUploadLocation() {
				return this.uploadLocation;
			}

			public void setUploadLocation(String uploadLocation) {
				this.uploadLocation = uploadLocation;
			}
			
			public Date getUploadDate() {
				return uploadDate;
			}

			public void setUploadDate(Date uploadDate) {
				this.uploadDate = uploadDate;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
				result = prime * result + ((uploadLocation == null) ? 0 : uploadLocation.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				UploadResponse other = (UploadResponse) obj;
				if (uploadDate == null) {
					if (other.uploadDate != null)
						return false;
				} else if (!uploadDate.equals(other.uploadDate))
					return false;
				if (uploadLocation == null) {
					if (other.uploadLocation != null)
						return false;
				} else if (!uploadLocation.equals(other.uploadLocation))
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "UploadResponse [uploadLocation=" + uploadLocation + ", uploadDate=" + uploadDate + "]";
			}
		}

