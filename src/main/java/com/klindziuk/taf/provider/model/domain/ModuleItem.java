/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "modules")
public class ModuleItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid")
  private String uuid;

  @NotEmpty
  @Size(min = 3, max = 30)
  @Column(name = "group_id")
  private String groupId;

  @NotEmpty
  @Size(min = 3, max = 30)
  @Column(name = "artifact_id")
  private String artifactId;

  @NotEmpty
  @Size(min = 3, max = 30)
  @Column(name = "display_name")
  private String displayName;

  @NotEmpty
  @Size(min = 3, max = 1000)
  @Column(name = "description")
  private String description;

  @NotEmpty
  @Size(min = 3, max = 30)
  @Column(name = "module_group")
  private String moduleGroup;

  @NotEmpty
  @Size(min = 3, max = 30)
  @Column(name = "version")
  private String version;

  @NotEmpty
  @Size(min = 3, max = 30)
  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_at")
  private Instant createdAt;

  @NotEmpty
  @Size(min = 3, max = 30)
  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "updated_at")
  private Instant updatedAt;

  // TODO(pklindziuk): remove when authentication will be enabled
  @PrePersist
  private void prePersist() {
    final String user = RandomStringUtils.randomAlphanumeric(8);
    this.createdBy = user;
    this.updatedBy = user;
    this.uuid = UUID.randomUUID().toString();
  }
}
